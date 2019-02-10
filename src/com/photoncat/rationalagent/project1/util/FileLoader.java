package com.photoncat.rationalagent.project1.util;

import java.io.*;

import static com.photoncat.rationalagent.project1.util.ExceptionHandling.panic;

/**
 * A class that loads a graph input file.
 * @author Xu Ke
 *
 */
public class FileLoader {
	/**
	 * Loads a graph form a stream.
	 */
	public static LoadedGraph load(Reader source) {
		// Create a tokenizer, either by file or by text.
		ConvenientStreamTokenizer tokens = new ConvenientStreamTokenizer(source);
		// Compile the program and return.
        LoadedGraph graph = null;
		try {
            graph = load(tokens);
		} catch (IOException e) {
			System.err.println("File format error.");
			System.exit(-1);
		}
		return graph;
	}
	/**
	 * Alias for text source.
	 */
	public static LoadedGraph load(String source) {
		return load(new StringReader(source));
	}
	/**
     * load a graph.
	 * @throws IOException
	 */
	private static LoadedGraph load(ConvenientStreamTokenizer tokens) throws IOException {
        LoadedGraph program = new LoadedGraph();
        int token = 0;
        // Parse vertices.
        token = tokens.nextToken();
        if (token != ConvenientStreamTokenizer.TT_WORD || !tokens.sval.equalsIgnoreCase("Vertices")) {
            panic("Missing 'Vertices' field.");
        }
        while (true) {
            if (!parseOptionalGraphNode(tokens, program)) break;
        }
        program.sortNodes();
        // Parse edges.
        token = tokens.nextToken();
        if (token != ConvenientStreamTokenizer.TT_WORD || !tokens.sval.equalsIgnoreCase("Edges")) {
            panic("Missing 'Edges' field.");
        }
        while (true) {
            if (!parseOptionalEdge(tokens, program)) break;
        }
        program.createEdgesCache();
        return program;
    }
    /**
     * @throws IllegalStateException when file is corrupted.
     */
    private static boolean parseOptionalGraphNode(ConvenientStreamTokenizer tokens, LoadedGraph program) throws IOException {
        if (tokens.nextToken() != ConvenientStreamTokenizer.TT_NUMBER) {
            tokens.pushBack();
            return false;
        }
        int id = (int) tokens.nval;
        if (tokens.nextToken() != ',') {
            panic("Unexpected token at line " + tokens.lineno());
        }
        if (tokens.nextToken() != ConvenientStreamTokenizer.TT_NUMBER) {
            panic("Unexpected token at line " + tokens.lineno());
        }
        int x = (int) tokens.nval;
        if (tokens.nextToken() != ',') {
            panic("Unexpected token at line " + tokens.lineno());
        }
        if (tokens.nextToken() != ConvenientStreamTokenizer.TT_NUMBER) {
            panic("Unexpected token at line " + tokens.lineno());
        }
        int y = (int) tokens.nval;
        program.addNode(id, x, y);
        return true;
    }
    /**
     * @throws IllegalStateException when file is corrupted.
     */
    private static boolean parseOptionalEdge(ConvenientStreamTokenizer tokens, LoadedGraph program) throws IOException {
        if (tokens.nextToken() != ConvenientStreamTokenizer.TT_NUMBER) {
            tokens.pushBack();
            return false;
        }
        int from = (int) tokens.nval;
        if (tokens.nextToken() != ',') {
            panic("Unexpected token at line " + tokens.lineno());
        }
        if (tokens.nextToken() != ConvenientStreamTokenizer.TT_NUMBER) {
            panic("Unexpected token at line " + tokens.lineno());
        }
        int to = (int) tokens.nval;
        if (tokens.nextToken() != ',') {
            panic("Unexpected token at line " + tokens.lineno());
        }
        if (tokens.nextToken() != ConvenientStreamTokenizer.TT_NUMBER) {
            panic("Unexpected token at line " + tokens.lineno());
        }
        int distance = (int) tokens.nval;
        program.addEdge(from, to, distance);
        return true;
    }
}
