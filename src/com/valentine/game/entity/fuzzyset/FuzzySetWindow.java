package com.valentine.game.entity.fuzzyset;

import java.awt.Color;

import javax.swing.JOptionPane;

import com.valentine.game.entity.base.*;
import com.valentine.game.entity.ui.*;
import com.valentine.game.utils.*;

public class FuzzySetWindow extends Container
{
	private final static int WIDTH       = 300;
	private final static int HEIGHT      = 600;
	private final static int PAD         = 10;
	private final static int WIDTH_PADED = 300 - 2*PAD;
	private final static int BTN_W       = 20;
	private final static int BTN_H       = 20;
	private final static int GRAPH_H     = 200;
	
	private final String name;
	
	private final GButton addBtn;
	private final FuzzySetGraph graph;
	private final FuzzySet set;
	
	private final GString nameStr;
	
	private final GString universumLabel;
	private final GScrollString universumStr;
	
	private final GString carrierLabel;
	private final GScrollString carrierStr;
	
	
	
	private boolean editingEnabled = false;
	
	public FuzzySetWindow(Container _container, String _name, FuzzySet _set, double _x, double _y)
	{
		super(_container, _x, _y, WIDTH, HEIGHT);
		name = _name;
		set = _set;
		
		int POSY = PAD;
		

		addBtn = new GButton(this, "+", PAD, POSY, BTN_W, BTN_H);
		POSY += BTN_H + PAD;
		
		graph = new FuzzySetGraph(this, set, PAD, POSY, WIDTH_PADED, GRAPH_H);
		POSY += GRAPH_H + PAD;
		graph.setDrawColor(ColorExt.randomColor(100, 255));
		graph.setFillColor(new Color(0,0,30));

		addBtn.addListener(() ->
		{
			String str = JOptionPane.showInputDialog("Enter elements to add:", "el1 prox1 el2 prox2 ...");
			addToSet(str);
		});
		
		nameStr = new GString(this, "Name:" + name, PAD, POSY, WIDTH_PADED, BTN_H);
		POSY += BTN_H + PAD;
		
		universumLabel = new GString(this, "Universum:", PAD, POSY, WIDTH_PADED, BTN_H);
		POSY += BTN_H + PAD;
		universumLabel.setBorderVisible(false);
		
		universumStr = new GScrollString(this, "", PAD, POSY, WIDTH_PADED, BTN_H);
		POSY += BTN_H + PAD;
		
		carrierLabel = new GString(this, "Carrier:", PAD, POSY, WIDTH_PADED, BTN_H);
		POSY += BTN_H + PAD;
		carrierLabel.setBorderVisible(false);
		
		carrierStr = new GScrollString(this, "", PAD, POSY, WIDTH_PADED, BTN_H);
		POSY += BTN_H + PAD;
	}
	
	public void setEditingEnabled(boolean _editingEnabled)
	{
		editingEnabled = _editingEnabled;
		
		addBtn.setEnabled(editingEnabled);
	}
	
	public void addToSet(String _elements)
	{
		set.add(_elements);
		onSetChange();
	}
	
	public void onSetChange()
	{
		universumStr.setText(set.getUniversum().toString());
		carrierStr.setText(set.generateCarrier().keySet().toString());
	}

}
