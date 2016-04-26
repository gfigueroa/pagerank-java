package pagerank;

import org.apache.log4j.Logger;

/**
 * PageRank class
 *
 * @author Gerardo Figueroa
 * Institute of Information Systems and Applications
 * National Tsing Hua University
 * Hsinchu, Taiwan
 * October 2013
 */
public class PageRank {
    
    // logging
    private static final Logger logger = Logger.getLogger(PageRank.class);
    
    private PageRankProperties pageRankProperties;

    /**
     * Protected members.
     */
    protected Graph graph = null;
    protected long start_time = 0L;
    protected long elapsed_time = 0L;

    // Public constructor (for use in other classes)
    public PageRank(PageRankProperties pageRankProperties) 
            throws Exception {
        this.pageRankProperties = pageRankProperties;
    }

    //////////////////////////////////////////////////////////////////////
    // access and utility methods
    //////////////////////////////////////////////////////////////////////
    /**
     * Re-initialize the timer.
     */
    public void initTime() {
        start_time = System.currentTimeMillis();
    }

    /**
     * Report the elapsed time with a label.
     */
    public void markTime(final String label) {
        elapsed_time = System.currentTimeMillis() - start_time;
        logger.info("ELAPSED_TIME:\t" + elapsed_time + "\t" + label);
    }

    /**
     * Accessor for the graph.
     */
    public Graph getGraph() {
        return graph;
    }

    /**
     * Main entry point.
     */
    public Node[] run(String graphFileDir) throws Exception {

        logger.info("Loading graph...");
        graph = Graph.loadGraph(graphFileDir, pageRankProperties.graphFileSeparator,
                pageRankProperties.dampingFactor,
                pageRankProperties.standardErrorThreshold);
        logger.info("Graph loaded successfully!");
        
        initTime();

        final int max_results = graph.size();

        logger.info("Running PageRank...");
        graph.runPageRank();
        graph.sortResults(max_results);
        logger.info("PageRank complete for this graph!");
        
        if (logger.isTraceEnabled()) {
            graph.printNodeList();
        }

        markTime("basic_pagerank");
        
        logger.info("GRAPH_SIZE:\t" + graph.size());

        return graph.node_list;
    }

}
