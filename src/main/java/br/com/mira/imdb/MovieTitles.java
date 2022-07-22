package br.com.mira.imdb;
import br.com.mira.imdb.accumulators.NameToPrincipalAccumulatorFn;
import br.com.mira.imdb.models.Principals;
import br.com.mira.imdb.models.TitleBasics;
import br.com.mira.imdb.models.result.QueryResult;
import br.com.mira.imdb.transforms.NameTransform;
import br.com.mira.imdb.transforms.PrincipalsTransform;
import br.com.mira.imdb.transforms.QueryTransform;
import br.com.mira.imdb.transforms.TitleBasicsTransform;
import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.io.TextIO;
import org.apache.beam.sdk.options.PipelineOptions;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.transforms.*;
import org.apache.beam.sdk.values.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Text;

import br.com.mira.imdb.models.Names;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MovieTitles {

    private static final Logger LOG = LoggerFactory.getLogger(MovieTitles.class);

    public static void main(String[] args) {
        var options = PipelineOptionsFactory.create();
        var pipeline = Pipeline.create(options);
        var pLinesName = pipeline.apply(TextIO.read().from("file:///home/miraman/imdb/min.name.basics.tsv"));
        var pNames = pLinesName.apply(ParDo.of(new NameTransform()));

		var pLinesPrincipals = pipeline.apply(TextIO.read().from("file:///home/miraman/imdb/min.title.principals.tsv"));
        var pPrincipals = pLinesPrincipals.apply(ParDo.of(new PrincipalsTransform()));
        //var view =  pPrincipals.apply(Combine.<Principals, Map<String, ArrayList<Principals>>>globally(new NameToPrincipalAccumulatorFn()).asSingletonView());
        var view =  pPrincipals
                .apply(MapElements
                        .into(TypeDescriptors.kvs(TypeDescriptors.strings(), new TypeDescriptor<Principals>() {}))
                        .via(input -> KV.of(input.getNconst(), input)))
                .apply(View.asMultimap());


        var pLinesTitleBasics = pipeline.apply(TextIO.read().from("file:///home/miraman/imdb/min.title.basics.tsv"));
        var viewTitleBasics = pLinesTitleBasics
                .apply(ParDo.of(new TitleBasicsTransform()))
                .apply(MapElements
                        .into(TypeDescriptors.kvs(TypeDescriptors.strings(), new TypeDescriptor<TitleBasics>() {
                        }))
                        .via(input -> KV.of(input.getTconst(), input)))
                .apply(View.asMap());


        var pResult = pNames.apply(ParDo.of(new DoFn<Names, QueryResult>() {
            @ProcessElement
            public void processElement(ProcessContext c) {
                Names element = c.element();
                Map<String, Iterable<Principals>> mapPrincipals = c.sideInput(view);
                Iterable<Principals> p = mapPrincipals.get(element.getNconst());
                Map<String, TitleBasics> mapTitle = c.sideInput(viewTitleBasics);
                mapTitle.get(p.getTconst());
                QueryResult q = new QueryResult();
                q.setName(element.getPrimaryName());
                c.output(q);
            }
        }).withSideInputs(view, viewTitleBasics));

        pNames.apply(ParDo.of(new DoFn<Names, String>() {
            @ProcessElement
            public void ProcessElement(ProcessContext c) {
                LOG.info(c.element().getPrimaryName());
                c.output(c.element().getPrimaryName());
            }
        }));

        pipeline.run();
    }

}

