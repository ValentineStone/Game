package com.valentine.game.entity.fuzzyset;

import java.awt.Color;
import java.util.*;

import javax.swing.JOptionPane;

import com.valentine.game.entity.base.*;
import com.valentine.game.entity.ui.*;
import com.valentine.game.utils.*;

public class FuzzySetWindow extends ContainerWindow
{
	public final static int WIDTH       = 300;
	public final static int HEIGHT      = 600;
	public final static int PAD         = 10;
	public final static int WIDTH_PADED = 300 - 2*PAD;
	public final static int BTN_W       = 20;
	public final static int BTN_H       = 20;
	public final static int GRAPH_H     = 200;
	
	private final GButton addBtn;
	private final FuzzySetGraph graph;
	private final FuzzySet set;
	
	private final GString universumLabel;
	private final GScrollString universumStr;
	
	private final GString carrierLabel;
	private final GScrollString carrierStr;
	
	private final GString breakpointsLabel;
	private final GScrollString breakpointsStr;
	
	private final GString coreLabel;
	private final GScrollString coreStr;
	
	
	
	private boolean editingEnabled = false;
	
	public FuzzySetWindow(Container _container, String _name, FuzzySet _set, double _x, double _y)
	{
		super(_container, _name, _x, _y, WIDTH, HEIGHT);
		set = _set;
		
		int POSY = PAD;
		

		addBtn = new GButton(this, "+", PAD, POSY, BTN_W, BTN_H);
		POSY += BTN_H + PAD;
		
		graph = new FuzzySetGraph(this, set, PAD, POSY, WIDTH_PADED, GRAPH_H);
		POSY += GRAPH_H + PAD;
		graph.setDrawColor(ColorExt.randomColor(100, 255));
		graph.setFillColor(new Color(0,0,30));

		addBtn.addListener
		(
			() ->
			{
				addToSet(JOptionPane.showInputDialog("Enter elements to add:", "el1 prox1 el2 prox2 ..."));
			}
		);
		
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
		
		breakpointsLabel = new GString(this, "Break points:", PAD, POSY, WIDTH_PADED, BTN_H);
		POSY += BTN_H + PAD;
		breakpointsLabel.setBorderVisible(false);
		
		breakpointsStr = new GScrollString(this, "", PAD, POSY, WIDTH_PADED, BTN_H);
		POSY += BTN_H + PAD;
		
		coreLabel = new GString(this, "Core:", PAD, POSY, WIDTH_PADED, BTN_H);
		POSY += BTN_H + PAD;
		coreLabel.setBorderVisible(false);
		
		coreStr = new GScrollString(this, "", PAD, POSY, WIDTH_PADED, BTN_H);
		POSY += BTN_H + PAD;
		

		onSetChange();
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
		breakpointsStr.setText(set.generateBreakpoints().toString());
		
		Set<Double> core = set.generateCore();
		
		if (core.size() > 1)
			coreLabel.setText("Multicore:");
		else if (core.size() == 1)
			coreLabel.setText("Unicore:");
		else
			coreLabel.setText("No core:");
		coreStr.setText(set.generateCore().toString());
	}

}
