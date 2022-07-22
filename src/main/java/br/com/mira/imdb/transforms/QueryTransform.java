package br.com.mira.imdb.transforms;

import br.com.mira.imdb.models.Names;
import br.com.mira.imdb.models.result.QueryResult;
import org.apache.beam.sdk.transforms.DoFn;

public class QueryTransform extends DoFn<Names, QueryResult> {
    @ProcessElement
    public void ProcessElement(@Element Names element, OutputReceiver<QueryResult> out, ProcessContext c) {
        QueryResult q = new QueryResult();

        q.setName(element.getPrimaryName());
        out.output(q);
    }
}
