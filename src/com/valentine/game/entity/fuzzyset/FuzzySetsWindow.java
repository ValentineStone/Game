package com.valentine.game.entity.fuzzyset;

import java.awt.Color;

import javax.swing.JOptionPane;

import com.valentine.game.entity.base.*;
import com.valentine.game.entity.ui.*;
import com.valentine.game.utils.ColorExt;
import com.valentine.game.utils.Ref;

public class FuzzySetsWindow extends ContainerWindow
{
	public FuzzySetsWindow(Container _container, double _x, double _y, double _width, double _height)
	{
		super(_container, _x, _y, _width, _height);

		final GButton btn = new GButton(this, "+", getWidth() - 23, 3, 20, 20);
		
		final FuzzySetGraph graph = new FuzzySetGraph(this, 3, 3, 200, 200);
		graph.setDrawColor(ColorExt.randomColor(100, 255));
		graph.setFillColor(new Color(0,0,30));
		
		final Ref<FuzzySet> setRef = graph.getSetRef();

		btn.addListener(() ->
		{
			String str = JOptionPane.showInputDialog("Enter elements to add:", "el1 prox1 el2 prox2 ...");
			setRef.get().add(str);
		});
	}

}
