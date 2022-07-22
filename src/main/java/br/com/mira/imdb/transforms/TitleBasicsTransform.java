package br.com.mira.imdb.transforms;

import br.com.mira.imdb.models.Names;
import br.com.mira.imdb.models.TitleBasics;
import org.apache.beam.sdk.transforms.DoFn;

public class TitleBasicsTransform extends DoFn<String, TitleBasics> {
    @ProcessElement
    public void ProcessElement(@Element String element, OutputReceiver<TitleBasics> out) {
        String[] columns = element.split("\t");
        TitleBasics titleBasics = new TitleBasics();
        titleBasics.setTconst(columns[0]);
        titleBasics.setTitleType(columns[1]);
        titleBasics.setPrimaryTitle(columns[2]);
        titleBasics.setOriginalTitle(columns[3]);
        titleBasics.setAdult(Integer.parseInt(columns[4]) == 1);
        if (!columns[5].equals("\\N"))
            titleBasics.setStartYear(Integer.parseInt(columns[5]));
        if (!columns[6].equals("\\N"))
            titleBasics.setEndYear(Integer.parseInt(columns[6]));
        if (!columns[7].equals("\\N"))
            titleBasics.setRuntimeMinutes(Integer.parseInt(columns[7]));
        titleBasics.setGenres(columns[8].split(","));
        out.output(titleBasics);
    }

}
