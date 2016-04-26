package pagerank;

import java.util.Properties;

/**
 * Properties for PageRank
 *
 * @author Gerardo Figueroa
 * Institute of Information Systems and Applications
 * National Tsing Hua University
 * Hsinchu, Taiwan
 * October 2013
 */
public class PageRankProperties {

    public final String graphFileSeparator;
    public final double standardErrorThreshold;
    public final double dampingFactor;

    public PageRankProperties (Properties props) throws Exception {

        try {
            this.graphFileSeparator = props.getProperty("graph_file_separator");
            this.standardErrorThreshold =
                    Double.parseDouble(props.getProperty("standard_error_threshold"));
            this.dampingFactor = 
                    Double.parseDouble(props.getProperty("damping_factor"));
        }
        catch (Exception e) {
            throw new Exception("Error parsing properties file!\n" + 
                    e.getMessage());
        }
    }

    @Override
    public String toString() {
        String string = "";

        string += "GRAPH_FILE_SEPARATOR = " + graphFileSeparator + "\n";
        string += "STANDARD_ERROR_THRESHOLD = " + standardErrorThreshold + "\n";
        string += "DAMPING_FACTOR = " + dampingFactor;

        return string;
    }

}
