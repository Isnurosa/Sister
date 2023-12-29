/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package compres20Okt;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;


/**
 *
 * @author Rosa
 */
public class StaticHuffmanCompression {
    public static Map<Character, String> buildHuffmanCodes(String text) {
        Map<Character, Integer> frequencyMap = new HashMap<>();
        for (char c : text.toCharArray()) {
            frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
        }

        PriorityQueue<HuffmanNode> priorityQueue = new PriorityQueue<>();
        for (char c : frequencyMap.keySet()) {
            priorityQueue.add(new HuffmanNode(c, frequencyMap.get(c)));
        }

        while (priorityQueue.size() > 1) {
            HuffmanNode left = priorityQueue.poll();
            HuffmanNode right = priorityQueue.poll();
            HuffmanNode parent = new HuffmanNode('\0', left.frequency + right.frequency);
            parent.left = left;
            parent.right = right;
            priorityQueue.add(parent);
        }

        HuffmanNode root = priorityQueue.poll();
        Map<Character, String> huffmanCodes = new HashMap<>();
        buildHuffmanCodesRecursive(root, "", huffmanCodes);

        return huffmanCodes;
    }

    private static void buildHuffmanCodesRecursive(HuffmanNode node, String code, Map<Character, String> huffmanCodes) {
        if (node == null) {
            return;
        }
        if (node.data != '\0') {
            huffmanCodes.put(node.data, code);
        }
        buildHuffmanCodesRecursive(node.left, code + "0", huffmanCodes);
        buildHuffmanCodesRecursive(node.right, code + "1", huffmanCodes);
    }

    public static String compress(String text, Map<Character, String> huffmanCodes) {
        StringBuilder compressed = new StringBuilder();
        for (char c : text.toCharArray()) {
            compressed.append(huffmanCodes.get(c));
        }
        return compressed.toString();
    }

    public static String decompress(String compressed, Map<Character, String> huffmanCodes) {
        Map<String, Character> reverseMap = new HashMap<>();
        for (Map.Entry<Character, String> entry : huffmanCodes.entrySet()) {
            reverseMap.put(entry.getValue(), entry.getKey());
        }

        StringBuilder decompressed = new StringBuilder();
        int i = 0;
        while (i < compressed.length()) {
            int j = i + 1;
            while (j <= compressed.length() && !reverseMap.containsKey(compressed.substring(i, j))) {
                j++;
            }
            decompressed.append(reverseMap.get(compressed.substring(i, j)));
            i = j;
        }
        return decompressed.toString();
    }

    public static void main(String[] args) {
        String text = "this is an example for huffman encoding";
        Map<Character, String> huffmanCodes = buildHuffmanCodes(text);
        String compressedData = compress(text, huffmanCodes);

        System.out.println("Original Data: " + text);
        System.out.println("Compressed Data: " + compressedData);

        String decompressedData = decompress(compressedData, huffmanCodes);
        System.out.println("Decompressed Data: " + decompressedData);
    }


}
