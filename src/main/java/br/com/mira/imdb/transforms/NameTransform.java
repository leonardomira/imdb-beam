package br.com.mira.imdb.transforms;

import br.com.mira.imdb.models.Names;
import org.apache.beam.sdk.transforms.DoFn;

public class NameTransform extends DoFn<String, Names> {
    @ProcessElement
    public void processElement(ProcessContext c) {
        String element = c.element();
        String[] columns = element.split("\t");
        Names names = new Names();
        names.setNconst(columns[0]);
        names.setPrimaryName(columns[1]);

        String sBirthYear = columns[2];
        if (!sBirthYear.equals("\\N"))
            names.setBirthYear(Integer.parseInt(sBirthYear));

        String sDeathYear = columns[3];
        if (!sDeathYear.equals("\\N"))
            names.setDeathYear(Integer.parseInt(sDeathYear));
        names.setPrimaryProfession(columns[4].split(","));
        names.setKnownForTitles(columns[5].split(","));
        c.output(names);
    }

}
