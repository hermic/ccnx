package com.ccnx_sb15gr3_Courier.app;

import org.achartengine.ChartFactory;
import org.achartengine.chart.BarChart.Type;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ReportGenerator {
	private Context context;
	
	public ReportGenerator(Context context) {
		this.context=context;
	}
	
	 private String[] mMonth = new String[] {
		        "Jan", "Feb" , "Mar", "Apr", "May", "Jun",
		        "Jul", "Aug" , "Sep", "Oct", "Nov", "Dec"
		    };
		 
		
		 
		    public void openChart(){
		        int[] x = { 0,1,2,3,4,5,6,7 };
		        int[] income = { 2000,2500,2700,3000,2800,3500,3700,3800};
		        int[] expense = {2200, 2700, 2900, 2800, 2600, 3000, 3300, 3400 };
		 
		        // Creating an  XYSeries for Income
		        XYSeries incomeSeries = new XYSeries("Income");
		        // Creating an  XYSeries for Expense
		        XYSeries expenseSeries = new XYSeries("Expense");
		        // Adding data to Income and Expense Series
		        for(int i=0;i<x.length;i++){
		            incomeSeries.add(i,income[i]);
		            expenseSeries.add(i,expense[i]);
		        }
		 
		        // Creating a dataset to hold each series
		        XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
		        // Adding Income Series to the dataset
		        dataset.addSeries(incomeSeries);
		        // Adding Expense Series to dataset
		        dataset.addSeries(expenseSeries);
		 
		        // Creating XYSeriesRenderer to customize incomeSeries
		        XYSeriesRenderer incomeRenderer = new XYSeriesRenderer();
		        incomeRenderer.setColor(Color.rgb(130, 130, 230));
		        incomeRenderer.setFillPoints(true);
		        incomeRenderer.setLineWidth(2);
		        incomeRenderer.setDisplayChartValues(true);
		 
		        // Creating XYSeriesRenderer to customize expenseSeries
		        XYSeriesRenderer expenseRenderer = new XYSeriesRenderer();
		        expenseRenderer.setColor(Color.rgb(220, 80, 80));
		        expenseRenderer.setFillPoints(true);
		        expenseRenderer.setLineWidth(2);
		        expenseRenderer.setDisplayChartValues(true);
		 
		        // Creating a XYMultipleSeriesRenderer to customize the whole chart
		        XYMultipleSeriesRenderer multiRenderer = new XYMultipleSeriesRenderer();
		        multiRenderer.setXLabels(0);
		        multiRenderer.setChartTitle("Income vs Expense Chart");
		        multiRenderer.setXTitle("Year 2012");
		        multiRenderer.setYTitle("Amount in Dollars");
		        multiRenderer.setZoomButtonsVisible(true);
		        for(int i=0; i< x.length;i++){
		            multiRenderer.addXTextLabel(i, mMonth[i]);
		        }
		 
		        // Adding incomeRenderer and expenseRenderer to multipleRenderer
		        // Note: The order of adding dataseries to dataset and renderers to multipleRenderer
		        // should be same
		        multiRenderer.addSeriesRenderer(incomeRenderer);
		        multiRenderer.addSeriesRenderer(expenseRenderer);
		 
		        // Creating an intent to plot bar chart using dataset and multipleRenderer
		        Intent intent = ChartFactory.getBarChartIntent(this.context, dataset, multiRenderer, Type.DEFAULT);
		        	
		        // Start Activity
		        this.context.startActivity(intent);
		 
		    }
}
