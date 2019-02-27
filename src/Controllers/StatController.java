/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Utils.MyDBcon;
import com.github.javafx.charts.zooming.ZoomManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.StackPane;

/**
 *
 * @author ASUS
 */
public class StatController {

    static String austria = "Austria";
    static String brazil = "Brazil";
    static String france = "France";
    static String italy = "Italy";
    static String usa = "USA";


    public static Scene loadStat() throws SQLException {

          ArrayList<String> pay = new ArrayList<>();
          HashMap<String,Integer> statis = new HashMap<String,Integer>();
        Connection cnx = MyDBcon.getInstance().getCon();

        String query = " SELECT COUNT(*) as nbr,Pays from evenement GROUP BY Pays ORDER by nbr DESC LIMIT 5 ;";

        PreparedStatement preparedStmt1 = cnx.prepareStatement(query);
        ResultSet rs = preparedStmt1.executeQuery(query);

       while (rs.next())
       {
           statis.put(rs.getString(2), rs.getInt(1));
           
           
           
                
       }
       
           
       
       
      
       
   

   

        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart bc = new BarChart(xAxis, yAxis);
        bc.setTitle("Country Summary");
        xAxis.setLabel("Country");
        yAxis.setLabel("Value");

        final XYChart.Series series1 = new XYChart.Series();
        series1.setName("2017");
        
       for(Entry<String,Integer>entry : statis.entrySet()){
          series1.getData().add(new XYChart.Data(entry.getKey(), entry.getValue()));

       }
        




        // DO NOT ADD DATA TO CHART
        // bc.getData().addAll(series1, series2, series3);
        final StackPane pane = new StackPane();
        pane.getChildren().add(bc);
        Scene zz = new Scene(pane, 500, 400);
        new ZoomManager(pane, bc, series1);
        return zz;
    }

}
