package com.valentine.game.entity.fuzzyset;

import java.awt.*;
import java.util.*;
import java.util.Map.*;
import java.util.function.*;

import javax.swing.*;

import com.valentine.game.entity.base.Container;
import com.valentine.game.entity.ui.*;
import com.valentine.game.utils.*;
import com.valentine.game.utils.math.*;
import com.valentine.game.utils.math.geom.*;

public class FuzzySetWindow extends ContainerWindow
{
	public final static int WIDTH              = 300;
	public final static int HEIGHT             = 960;
	public final static int PAD                = 10;
	public final static int WIDTH_PADED        = WIDTH - 2*PAD;
	public final static int BTN_W              = 20;
	public final static int BTN_H              = 25;
	public final static int GRAPH_H            = 200;
	public final static int DEFAULT_APRX_LEVEL = 5;
	
	private int aprxPower = DEFAULT_APRX_LEVEL;
	private Polinom approximation;
	
	private final GButton addBtn;
	private final GButton remBtn;
	private final GButton normBtn;
	private final GButton updateBtn;
	private final GButton powBtn;
	private final GButton conBtn;
	private final GButton dilBtn;
	private final GButton clearBtn;
	
	private FuzzySet set;
	
	private final FuzzySetGraph graph;
	private final FuzzySetGraph graphCon;
	private final FuzzySetGraph graphDil;
	private final GFunctionBox  graphAprx;
	
	private final GButton conShowBtn;
	private final GButton dilShowBtn;
	private final GButton aprxShowBtn;
	
	private final Color conColor  = Color.GREEN;
	private final Color dilColor  = Color.RED;
	private final Color aprxColor = ColorExt.LIGHT_BLUE;
	
	private final GString setLabel;
	private final GScrollString setStr;
	
	private final GString aprxLabel;
	private final GScrollString aprxStr;
	
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
	

	private final GString heightLabel;
	private final GScrollString heightStr;
	
	private final GString linearLabel;
	private final GScrollString linearStr;
	
	private final GString quadLabel;
	private final GScrollString quadStr;
	
	
	
	private boolean editingEnabled = false;
	
	public FuzzySetWindow(Container _container, String _name, FuzzySet _set, double _x, double _y)
	{
		super(_container, _name, _x, _y, WIDTH, HEIGHT);
		set = _set;
		
		int POSY = PAD;
		
		
		Color frameColor = ColorExt.randomColor(100, 255);
		

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
		updateBtn = new GButton(this, "U", 4*PAD + 3*BTN_W, POSY, BTN_W, BTN_H);
		updateBtn.addListener
		(
			() ->
			{
				onSetChange();
			}
		);
		powBtn = new GButton(this, "^", 5*PAD + 4*BTN_W, POSY, BTN_W, BTN_H);
		powBtn.addListener
		(
			() ->
			{
				try
				{
					set.pow(Double.valueOf(JOptionPane.showInputDialog("Enter power:", "pow")));
				}
				catch (Exception _exc)
				{}
				
				onSetChange();
			}
		);
		conBtn = new GButton(this, "c", 6*PAD + 5*BTN_W, POSY, BTN_W, BTN_H);
		conBtn.addListener
		(
			() ->
			{
				set.con();
				onSetChange();
			}
		);
		dilBtn = new GButton(this, "d", 7*PAD + 6*BTN_W, POSY, BTN_W, BTN_H);
		dilBtn.addListener
		(
			() ->
			{
				set.dil();
				onSetChange();
			}
		);
		clearBtn = new GButton(this, "~", 8*PAD + 7*BTN_W, POSY, BTN_W, BTN_H);
		clearBtn.addListener
		(
			() ->
			{
				set.clear();
				onSetChange();
			}
		);
		POSY += BTN_H + PAD;
		
		graphCon = new FuzzySetGraph(this, set.clone(), PAD, POSY, WIDTH_PADED, GRAPH_H);
		graphCon.getSet().con();
		graphCon.setFillColor(ColorExt.TRANSPARENT);
		graphCon.setDrawColor(conColor);
		graphCon.setPaintable(false);
		
		graphDil = new FuzzySetGraph(this, set.clone(), PAD, POSY, WIDTH_PADED, GRAPH_H);
		graphDil.getSet().dil();
		graphDil.setFillColor(ColorExt.TRANSPARENT);
		graphDil.setDrawColor(dilColor);
		graphDil.setPaintable(false);
		
		graph = new FuzzySetGraph(this, set, PAD, POSY, WIDTH_PADED, GRAPH_H);
		graph.setFillColor(ColorExt.TRANSPARENT);
		graph.setDrawColor(frameColor);
		
		graphAprx = new GFunctionBox
		(
			this,
			PAD  +            FuzzySetGraph.DEFAULT_PADDINGS,
			POSY +            FuzzySetGraph.DEFAULT_PADDINGS,
			WIDTH_PADED - 2 * FuzzySetGraph.DEFAULT_PADDINGS,
			GRAPH_H     - 2 * FuzzySetGraph.DEFAULT_PADDINGS
		);
		graphAprx.setFillColor(ColorExt.TRANSPARENT);
		graphAprx.setDrawColor(aprxColor);
		graphAprx.setPaintable(false);
		graphAprx.setDrawFunctionOnly(true);
		
		POSY += GRAPH_H + PAD;
		
		
		
		conShowBtn = new GButton(this, "CON", 1*PAD + 0*BTN_W, POSY, 3*BTN_W, BTN_H);
		conShowBtn.addListener
		(
			() ->
			{
				graphCon.setPaintable(!graphCon.isPaintable());
				conShowBtn.setDrawColor(conShowBtn.getDrawColor().equals(conColor) ? Color.WHITE : conColor);
			}
		);
		dilShowBtn = new GButton(this, "DIL", 2*PAD + 3*BTN_W, POSY, 3*BTN_W, BTN_H);
		dilShowBtn.addListener
		(
			() ->
			{
				graphDil.setPaintable(!graphDil.isPaintable());
				dilShowBtn.setDrawColor(dilShowBtn.getDrawColor().equals(dilColor) ? Color.WHITE : dilColor);
			}
		);
		aprxShowBtn = new GButton(this, "APRX", 3*PAD + 6*BTN_W, POSY, 3*BTN_W, BTN_H);
		aprxShowBtn.addListener
		(
			() ->
			{
				if (!graphAprx.isPaintable())
				{
					String powerString = JOptionPane.showInputDialog("Enter aproximation's max power:", "1 - linear, 2 - qadratic, 3 - cubic...");
					int power;
					try
					{
						power = Integer.valueOf(powerString) + 1;
						
						if (power < 1)
							return;
					}
					catch (Exception _exc)
					{
						return;
					}
					
					if (aprxPower != power)
					{
						aprxPower = power;
						reAproximate();
					}
				}
				
				graphAprx.setPaintable(!graphAprx.isPaintable());
				aprxShowBtn.setDrawColor(aprxShowBtn.getDrawColor().equals(aprxColor) ? Color.WHITE : aprxColor);
			}
		);
		
		POSY += BTN_H + PAD;
		
		
		
		setLabel = new GString(this, "Set:", PAD, POSY, WIDTH_PADED, BTN_H);
		POSY += BTN_H + PAD;
		setLabel.setBorderVisible(false);
		
		setStr = new GScrollString(this, "-", PAD, POSY, WIDTH_PADED, BTN_H);
		POSY += BTN_H + PAD;
		setStr.setDrawColor(frameColor);
		
		aprxLabel = new GString(this, "Approximation:", PAD, POSY, WIDTH_PADED, BTN_H);
		POSY += BTN_H + PAD;
		setLabel.setBorderVisible(false);
		
		aprxStr = new GScrollString(this, "-", PAD, POSY, WIDTH_PADED, BTN_H);
		POSY += BTN_H + PAD;
		aprxStr.setDrawColor(frameColor);
		
		universumLabel = new GString(this, "Universum:", PAD, POSY, WIDTH_PADED, BTN_H);
		POSY += BTN_H + PAD;
		universumLabel.setBorderVisible(false);
		
		universumStr = new GScrollString(this, "-", PAD, POSY, WIDTH_PADED, BTN_H);
		POSY += BTN_H + PAD;
		universumStr.setDrawColor(frameColor);
		
		carrierLabel = new GString(this, "Carrier:", PAD, POSY, WIDTH_PADED, BTN_H);
		POSY += BTN_H + PAD;
		carrierLabel.setBorderVisible(false);
		
		carrierStr = new GScrollString(this, "-", PAD, POSY, WIDTH_PADED, BTN_H);
		POSY += BTN_H + PAD;
		carrierStr.setDrawColor(frameColor);
		
		breakpointsLabel = new GString(this, "Break points:", PAD, POSY, WIDTH_PADED, BTN_H);
		POSY += BTN_H + PAD;
		breakpointsLabel.setBorderVisible(false);
		
		breakpointsStr = new GScrollString(this, "-", PAD, POSY, WIDTH_PADED, BTN_H);
		POSY += BTN_H + PAD;
		breakpointsStr.setDrawColor(frameColor);
		
		bordersLabel = new GString(this, "Borders:", PAD, POSY, WIDTH_PADED, BTN_H);
		POSY += BTN_H + PAD;
		bordersLabel.setBorderVisible(false);
		
		bordersStr = new GScrollString(this, "-", PAD, POSY, WIDTH_PADED, BTN_H);
		POSY += BTN_H + PAD;
		bordersStr.setDrawColor(frameColor);
		
		coreLabel = new GString(this, "Core:", PAD, POSY, WIDTH_PADED, BTN_H);
		POSY += BTN_H + PAD;
		coreLabel.setBorderVisible(false);
		
		coreStr = new GScrollString(this, "-", PAD, POSY, WIDTH_PADED, BTN_H);
		POSY += BTN_H + PAD;
		coreStr.setDrawColor(frameColor);
		
		unimodalStr = new GString(this, "-", PAD, POSY, WIDTH / 2. - 3 * PAD / 2., BTN_H);
		normalityStr = new GString(this, "-", (WIDTH + PAD) / 2., POSY, WIDTH / 2. - 3 * PAD / 2., BTN_H);
		POSY += BTN_H + PAD;
		unimodalStr.setDrawColor(frameColor);
		normalityStr.setDrawColor(frameColor);
		
		heightLabel = new GString(this, "Height:", PAD, POSY, WIDTH / 2. - 3 * PAD / 2., BTN_H);
		heightLabel.setBorderVisible(false);
		
		heightStr = new GScrollString(this, "-", (WIDTH + PAD) / 2., POSY, WIDTH / 2. - 3 * PAD / 2., BTN_H);
		POSY += BTN_H + PAD;
		heightStr.setDrawColor(frameColor);
		
		linearLabel = new GString(this, "Lin. Fuzz:", PAD, POSY, WIDTH / 2. - 3 * PAD / 2., BTN_H);
		linearLabel.setBorderVisible(false);
		
		linearStr = new GScrollString(this, "-", (WIDTH + PAD) / 2., POSY, WIDTH / 2. - 3 * PAD / 2., BTN_H);
		POSY += BTN_H + PAD;
		linearStr.setDrawColor(frameColor);
		
		quadLabel = new GString(this, "Quad Fuzz:", PAD, POSY, WIDTH / 2. - 3 * PAD / 2., BTN_H);
		quadLabel.setBorderVisible(false);
		
		quadStr = new GScrollString(this, "-", (WIDTH + PAD) / 2., POSY, WIDTH / 2. - 3 * PAD / 2., BTN_H);
		POSY += BTN_H + PAD;
		quadStr.setDrawColor(frameColor);
		
		
		
		onSetChange();
	}
	
	public void setEditingEnabled(boolean _editingEnabled)
	{
		editingEnabled = _editingEnabled;
		
		addBtn.setEnabled(editingEnabled);
		remBtn.setEnabled(editingEnabled);
		normBtn.setEnabled(editingEnabled);
	}
	
	
	
	public FuzzySet getSet()
	{
		return set;
	}

	public void setSet(FuzzySet _set)
	{
		set = _set;
		onSetChange();
	}

	
	
	public void onSetChange()
	{
		setStr.setText(set.getMap().toString());
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
		linearStr.setText(String.valueOf(set.evalLinearFuzzyness()));
		quadStr.setText(String.valueOf(set.evalQuadraticFuzzyness()));
		
		graphCon.setSet(set.clone());
		graphCon.getSet().con();
		graphDil.setSet(set.clone());
		graphDil.getSet().dil();
		
		reAproximate();
	}
	
	public void reAproximate()
	{
		ArrayList<Dot2d> dots = IterableExt.asArrayList(IterableExt.asIterable(set));
		
		approximation = Approximation.approximate(dots.toArray(new Dot2d[dots.size()]), aprxPower);
		
		graphAprx.set((x) -> approximation.applyAsDouble(x), set.getMin(), set.getMax(), 0.1, graphAprx.getHeight(), graphAprx.getHeight());
		
		aprxStr.setText(Arrays.toString(approximation.getCoefficients()));
	}
}
