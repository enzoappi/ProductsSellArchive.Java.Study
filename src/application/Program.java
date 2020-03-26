/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import entities.Product;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

/**
 *
 * @author enzoappi
 */
public class Program {
    
    public static void main(String[] args) throws ParseException{
     
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        
        List<Product> prodList = new ArrayList<>();
        
        System.out.println("Source file path: ");
        String srcFilePath = sc.nextLine();
        
        File srcFile = new File(srcFilePath);
        String outDirectoryPath = srcFile.getParent();
        
        File outDirectoryCreation = new File(outDirectoryPath + "/out");
        outDirectoryCreation.mkdir(); //creating the directory ./out
        
        String outFileName = outDirectoryPath + "/out/summary.csv";
        
        try(BufferedReader br = new BufferedReader(new FileReader(srcFile))) {
            
            String lineReadInCsv = br.readLine();
            
            while(lineReadInCsv != null) {
                String[] fieldSplited = lineReadInCsv.split(",");
                String name = fieldSplited[0];
                double price = Double.parseDouble(fieldSplited[1]);
                int quantity = Integer.parseInt(fieldSplited[2]);
                
                prodList.add(new Product(name, price, quantity));
                
                lineReadInCsv = br.readLine();
                
            }
            try(BufferedWriter bw = new BufferedWriter(new FileWriter(outFileName))) {
                bw.write("Sell Data Summary:\n");
                for(Product p : prodList) {
                    bw.write(p.getName() + ", US$" + p.totalValue());
                    bw.newLine();
                }
            }
            catch(IOException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        catch(IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        sc.close();
    }
}
