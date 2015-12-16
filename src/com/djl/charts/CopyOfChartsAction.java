package com.djl.charts;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import ChartDirector.BarLayer;
import ChartDirector.Chart;
import ChartDirector.XYChart;

@Controller @Scope("prototype")
public class CopyOfChartsAction {

	public String test(){
		// The data for the bar chart
		double[] data = {85, 156, 179.5, 211, 123};

		// The labels for the bar chart
		String[] labels = {"Mon", "Tue", "Wed", "Thu", "Fri"};

		// Create a XYChart object of size 250 x 250 pixels
		XYChart c = new XYChart(500, 500);

		// Set the plotarea at (30, 20) and of size 400 x 400 pixels
		c.setPlotArea(30, 20, 400, 400);

		// Add a bar chart layer using the given data
		c.addBarLayer(data);

		// Set the labels on the x axis.
		c.xAxis().setLabels(labels);

		// Output the chart
		String chart1URL = c.makeSession(ServletActionContext.getRequest(), "chart1");
		System.out.println(chart1URL);
		// Include tool tip for the chart
		String imageMap1 = c.getHTMLImageMap("", "", "title='{xLabel}: {value} GBytes'");
		
		//把chart1URL、imageMap1放入ActionContext
//		ServletActionContext.getRequest().setAttribute("chart1URL", chart1URL);
//		ServletActionContext.getRequest().setAttribute("imageMap1", imageMap1);
		ActionContext.getContext().put("chart1URL", chart1URL);
		ActionContext.getContext().put("imageMap1", imageMap1);
		
		System.out.println("图表测试页面");
		return "test";
	}
	
	public String test01() throws UnsupportedEncodingException{
		// The data for the bar chart
		double[] data = {85, 156, 179, 211, 123, 189, 166};

		String Mon =URLEncoder.encode("星期一","utf-8");
		// The labels for the bar chart
		String[] labels = {Mon, "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};

		// Create a XYChart object of size 600 x 360 pixels
		XYChart c = new XYChart(600, 360);

		//设置字体
		c.setDefaultFonts("宋体");
		
		// Set the plotarea at (70, 20) and of size 500 x 300 pixels, with transparent background and border
		// and light grey (0xcccccc) horizontal grid lines
		c.setPlotArea(70, 20, 500, 300, Chart.Transparent, -1, Chart.Transparent, 0xcccccc);

		// Set the x and y axis stems to transparent and the label font to 12pt Arial
		c.xAxis().setColors(Chart.Transparent);
		c.yAxis().setColors(Chart.Transparent);
		c.xAxis().setLabelStyle("Arial", 12);
		c.yAxis().setLabelStyle("Arial", 12);

		// Add a blue (0x6699bb) bar chart layer using the given data
		BarLayer layer = c.addBarLayer(data, 0x6699bb);

		// Use bar gradient lighting with the light intensity from 0.8 to 1.3
		layer.setBorderColor(Chart.Transparent, Chart.barLighting(0.8, 1.3));

		// Set rounded corners for bars
		layer.setRoundedCorners();

		// Display labela on top of bars using 12pt Arial font
		layer.setAggregateLabelStyle("Arial", 12);

		// Set the labels on the x axis.
		c.xAxis().setLabels(labels);

		// For the automatic y-axis labels, set the minimum spacing to 40 pixels.
		c.yAxis().setTickDensity(40);

		// Add a title to the y axis using dark grey (0x555555) 14pt Arial Bold font
//		c.yAxis().setTitle("Y-Axis Title Placeholder", "Arial Bold", 14, 0x555555);
		c.addTitle("Y-Axis Title Placeholder", "Arial Bold", 14, 0x555555);
		// Output the chart
		String chart1URL = c.makeSession(ServletActionContext.getRequest(), "chart1");

		// Include tool tip for the chart
		String imageMap1 = c.getHTMLImageMap("", "", "title='{xLabel}: ${value}M'");

		ActionContext.getContext().put("chart1URL", chart1URL);
		ActionContext.getContext().put("imageMap1", imageMap1);
		return "test01";
	}
	
	public CopyOfChartsAction() {
		System.out.println("ChartsAction init ...");
	}
}
