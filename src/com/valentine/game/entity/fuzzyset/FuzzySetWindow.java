package com.valentine.game.entity.fuzzyset;

import java.awt.Color;
import java.util.*;
import java.util.Map.*;

import javax.swing.JOptionPane;

import com.valentine.game.entity.base.*;
import com.valentine.game.entity.ui.*;
import com.valentine.game.utils.*;

public class FuzzySetWindow extends ContainerWindow
{
	public final static int WIDTH       = 300;
	public final static int HEIGHT      = 900;
	public final static int PAD         = 10;
	public final static int WIDTH_PADED = 300 - 2*PAD;
	public final static int BTN_W       = 20;
	public final static int BTN_H       = 20;
	public final static int GRAPH_H     = 200;
	
	private final GButton addBtn;
	private final GButton remBtn;
	private final GButton normBtn;
	
	private final FuzzySetGraph graph;
	private final FuzzySet set;
	
	private final GString setLabel;
	private final GScrollString setStr;
	
	private final GString universumLabel;
	private final GScrollString universumStr;
	
	private final GString carrierLabel;
	private final GScrollString carrierStr;
	
	private final GString breakpointsLabel;
	private final GScrollString breakpointsStr;
	
	private final GString bordersLabel;
	private final GScrollString bordersStr;
	
	private final GString coreLabel;
	private final GScrollString coreStr;
	
	private final GString unimodalStr;
	private final GString normalityStr;
	
	private final GString heightStr;
	private final GString heightLabel;
	
	
	
	private boolean editingEnabled = false;
	
	public FuzzySetWindow(Container _container, String _name, FuzzySet _set, double _x, double _y)
	{
		super(_container, _name, _x, _y, WIDTH, HEIGHT);
		set = _set;
		
		int POSY = PAD;
		

		addBtn = new GButton(this, "+", 1*PAD + 0*BTN_W, POSY, BTN_W, BTN_H);
		addBtn.addListener
		(
			() ->
			{
				set.add(JOptionPane.showInputDialog("Enter elements to add:", "el1 prox1 el2 prox2 ..."));
				onSetChange();
			}
		);
		remBtn = new GButton(this, "-", 2*PAD + 1*BTN_W, POSY, BTN_W, BTN_H);
		remBtn.addListener
		(
			() ->
			{
				set.remove(JOptionPane.showInputDialog("Enter elements to remove:", "el1 el2 el3 ..."));
				onSetChange();
			}
		);
		normBtn = new GButton(this, "N", 3*PAD + 2*BTN_W, POSY, BTN_W, BTN_H);
		normBtn.addListener
		(
			() ->
			{
				set.normalize();
				onSetChange();
			}
		);
		POSY += BTN_H + PAD;
		
		graph = new FuzzySetGraph(this, set, PAD, POSY, WIDTH_PADED, GRAPH_H);
		POSY += GRAPH_H + PAD;
		graph.setDrawColor(ColorExt.randomColor(100, 255));
		graph.setFillColor(new Color(0,0,30));
		
		setLabel = new GString(this, "Set:", PAD, POSY, WIDTH_PADED, BTN_H);
		POSY += BTN_H + PAD;
		setLabel.setBorderVisible(false);
		
		setStr = new GScrollString(this, "-", PAD, POSY, WIDTH_PADED, BTN_H);
		POSY += BTN_H + PAD;
		
		universumLabel = new GString(this, "Universum:", PAD, POSY, WIDTH_PADED, BTN_H);
		POSY += BTN_H + PAD;
		universumLabel.setBorderVisible(false);
		
		universumStr = new GScrollString(this, "-", PAD, POSY, WIDTH_PADED, BTN_H);
		POSY += BTN_H + PAD;
		
		carrierLabel = new GString(this, "Carrier:", PAD, POSY, WIDTH_PADED, BTN_H);
		POSY += BTN_H + PAD;
		carrierLabel.setBorderVisible(false);
		
		carrierStr = new GScrollString(this, "-", PAD, POSY, WIDTH_PADED, BTN_H);
		POSY += BTN_H + PAD;
		
		breakpointsLabel = new GString(this, "Break points:", PAD, POSY, WIDTH_PADED, BTN_H);
		POSY += BTN_H + PAD;
		breakpointsLabel.setBorderVisible(false);
		
		breakpointsStr = new GScrollString(this, "-", PAD, POSY, WIDTH_PADED, BTN_H);
		POSY += BTN_H + PAD;
		
		bordersLabel = new GString(this, "Borders:", PAD, POSY, WIDTH_PADED, BTN_H);
		POSY += BTN_H + PAD;
		bordersLabel.setBorderVisible(false);
		
		bordersStr = new GScrollString(this, "-", PAD, POSY, WIDTH_PADED, BTN_H);
		POSY += BTN_H + PAD;
		
		coreLabel = new GString(this, "Core:", PAD, POSY, WIDTH_PADED, BTN_H);
		POSY += BTN_H + PAD;
		coreLabel.setBorderVisible(false);
		
		coreStr = new GScrollString(this, "-", PAD, POSY, WIDTH_PADED, BTN_H);
		POSY += BTN_H + PAD;
		
		unimodalStr = new GString(this, "-", PAD, POSY, WIDTH / 2. - 3 * PAD / 2., BTN_H);
		normalityStr = new GString(this, "-", (WIDTH + PAD) / 2., POSY, WIDTH / 2. - 3 * PAD / 2., BTN_H);
		POSY += BTN_H + PAD;
		
		heightLabel = new GString(this, "Height:", PAD, POSY, WIDTH / 2. - 3 * PAD / 2., BTN_H);
		heightLabel.setBorderVisible(false);
		
		heightStr = new GString(this, "-", (WIDTH + PAD) / 2., POSY, WIDTH / 2. - 3 * PAD / 2., BTN_H);
		POSY += BTN_H + PAD;
		
		
		
		onSetChange();
	}
	
	public void setEditingEnabled(boolean _editingEnabled)
	{
		editingEnabled = _editingEnabled;
		
		addBtn.setEnabled(editingEnabled);
		remBtn.setEnabled(editingEnabled);
		normBtn.setEnabled(editingEnabled);
	}
	
	public void addToSet(String _elements)
	{
		set.add(_elements);
		onSetChange();
	}
	
	public void onSetChange()
	{
		setStr.setText(set.getSet().toString());
		universumStr.setText(set.getUniversum().toString());
		carrierStr.setText(set.generateCarrier().keySet().toString());
		breakpointsStr.setText(set.generateBreakpoints().toString());
		bordersStr.setText(set.generateBorders().toString());
		
		Set<Double> core = set.generateCore();
		
		if (core.size() > 1)
			coreLabel.setText("Multicore:");
		else if (core.size() == 1)
			coreLabel.setText("Unicore:");
		else
			coreLabel.setText("No core:");
		
		coreStr.setText(core.toString());
		
		Entry<Double, Integer> modality = set.generateModality();
		
		unimodalStr.setText(modality.getValue() == 1 ? "Unimodal" : "Non-unim.");
		normalityStr.setText(core.size() == 0 ? "Subnorm." : "Normal");
		
		heightStr.setText(modality.getKey().toString());
	}

}
