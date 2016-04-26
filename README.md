# Java PageRank implementation

Implementation of the PageRank algorithm by Page et al.
[Page, L. et al. "The PageRank citation ranking: bringing order to the web." (1999).]
Ranks nodes in a weighted, directed and labeled graph.

## Installation

Requires Java 7 and NetBeans 7.2

## Usage

The Graph Matrix File (args[1]) must be in adjacency matrix format
with the node labels only on the top row (no labels on first column).
The rows after the first row contain the edge weights (1 for unweighted).
The separator between labels and edge weights is specified in the properties file.
Example (separator is a tab):
`label1	label2	label3 ...`
`weight_n1n1	weight_n1n2	weight_n1n3 ...`
`weight_n2n1	weight_n2n2	weight n2n3 ...`
`...`

To run jar file:

`java -jar PageRank.jar properties_file graph_file`

Example:

`java -jar dist\PageRank.jar res\default.properties tests\pagerank_test_data.txt`

## Contributing

1. Fork it!
2. Create your feature branch: `git checkout -b my-new-feature`
3. Commit your changes: `git commit -am 'Add some feature'`
4. Push to the branch: `git push origin my-new-feature`
5. Submit a pull request :D

## History

TODO: Write history

## Credits
Written by Gerardo Figueroa.