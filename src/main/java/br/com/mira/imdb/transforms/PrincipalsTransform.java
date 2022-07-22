package br.com.mira.imdb.transforms;

import br.com.mira.imdb.models.Names;
import br.com.mira.imdb.models.Principals;
import org.apache.beam.sdk.transforms.DoFn;

public class PrincipalsTransform extends DoFn<String, Principals> {
    @ProcessElement
    public void ProcessElement(@Element String element, OutputReceiver<Principals> out) {
        String[] columns = element.split("\t");
        Principals principals = new Principals();
        principals.setTconst(columns[0]);
        principals.setOrdering(Integer.parseInt(columns[1]));
        principals.setNconst(columns[2]);
        principals.setCategory(columns[3]);
        principals.setJob(columns[4]);
        principals.setCharacters(columns[5]);
        out.output(principals);
    }

}
