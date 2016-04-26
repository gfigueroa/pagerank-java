package pagerank;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import org.apache.log4j.Logger;

/**
 * Main class for running PageRank
 *
 * @author Gerardo Figueroa
 * Institute of Information Systems and Applications
 * National Tsing Hua University
 * Hsinchu, Taiwan
 * October 2013
 */
public class Main {

    private static final Logger logger = Logger.getLogger(Main.class);
    
    /**
     * Print the results in CSV format using the given separator.
     * @param nodeList: the list of ranked graph nodes
     * @param separator: the separator used in the CSV output
     */
    private static void printResultsCSV(Node[] nodeList, String separator) {
        System.out.println("Number" + separator + "Node data" + 
                separator + "Rank" + separator + "Incoming" + separator + "Outgoing");
        for (Node node : nodeList) {
            System.out.println(
                    node.key + separator +
                    node.data + separator +
                    node.rank + separator +
                    node.incomingEdges.size() + separator + 
                    node.outgoingEdges.size());
        }
    }

    /**
     * Main entry point for the PageRank algorithm.
     * The Graph Matrix File (args[1]) must be in adjacency matrix format
     * with the node labels only on the top row (no labels on first column).
     * The rows after the first row contain the edge weights (1 for unweighted).
     * The separator between labels and edge weights is specified in the properties
     * file.
     * Example (separator is space):
     * label1 label2 label3 ...
     * weight_n1n1 weight_n1n2 weight_n1n3 ...
     * weight_n2n1 weight_n2n2 weight n2n3 ...
     * ...
     * @param args
     */
    public static void main(String[] args) {
        /*
         * Arg 0: Properties file
         * Arg 1: Graph matrix file
         */
        try {
            
            if (args.length == 2) {

                // Load PageRank Properties
                FileInputStream fis = new FileInputStream(args[0]);
                Properties props = new Properties();
                props.load(fis);
                PageRankProperties pageRankProperties = new PageRankProperties(props);

                // Load graph matrix file
                String graphFileDir = args[1];

                logger.info("");
                logger.info("Starting PageRank...");
                logger.info("PageRank Properties:\n" + pageRankProperties.toString());
                logger.info("");

                final PageRank pageRank = new PageRank(pageRankProperties);

                // Run PageRank
                Node[] nodeList = pageRank.run(graphFileDir);

                printResultsCSV(nodeList, pageRankProperties.graphFileSeparator);

                logger.info("");
                logger.info("PageRank completed!");
                logger.info("");
            }
            else {
                logger.error("Wrong arguments. Usage:\n"
                        + "PageRank properties_file graph_file");
            }

        }
        catch (FileNotFoundException fnfe) {
            logger.error("Properties file not found! " + fnfe.getMessage());
        }
        catch (IOException ioe) {
            logger.error("IO Exception! " + ioe.getMessage());
        }
        catch (Exception e) {
            logger.error("Exception in PageRank Main: " + e.getMessage());
        }
    }
}
