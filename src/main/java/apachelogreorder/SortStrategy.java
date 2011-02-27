/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package apachelogreorder;

import java.util.Vector;
import java.text.SimpleDateFormat;

/**
 *
 * @author simone
 */
public class SortStrategy {
    static final int SORT_DATETIME = 1;
    static final int SORT_FILENAME = 2;
    static final int SORT_DIRNAME = 4;
    static final int SORT_METADATA = 8;

    int[] sortStrategyOrder = {SORT_DIRNAME,SORT_DATETIME,SORT_FILENAME};
    Vector<String> metadataOrder = new Vector<String>();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

    public Vector<String> getRelativePath(ApacheLogFile alf) {
        Vector<String> result = new Vector<String>();

        for (int i : sortStrategyOrder) {
            switch(i) {
                case SORT_DATETIME:
                    result.add(sdf.format(alf.getLogDate()));
                    break;

                case SORT_FILENAME:
                    result.add(alf.getSite());
                    break;

                case SORT_DIRNAME:
                    result.add(alf.getOrigin().getName());
                    break;

                case SORT_METADATA:
                    result.add(alf.getOrigin().getName());
                    break;

            }

        }
    }

}
