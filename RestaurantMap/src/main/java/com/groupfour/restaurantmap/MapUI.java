/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.groupfour.restaurantmap;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JTextField;
import javax.swing.JToolTip;

import org.jxmapviewer.JXMapKit;
import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.OSMTileFactoryInfo;
import org.jxmapviewer.viewer.DefaultTileFactory;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.TileFactoryInfo;


/**
 *
 * @author jesusalvarado
 */
public class MapUI {
    
    public void setUpUI()
    {
        //https://github.com/msteiger/jxmapviewer2
        //https://mvnrepository.com/artifact/org.jxmapviewer/jxmapviewer2/2.6
        //https://web.archive.org/web/20100309081606/http://today.java.net/pub/a/today/2007/10/30/building-maps-into-swing-app-with-jxmapviewer.html
        //https://web.archive.org/web/20100311151851/http://www.openstreetmap.org/
        
        final JXMapKit jXMapKit = new JXMapKit();
        TileFactoryInfo info = new OSMTileFactoryInfo();
        DefaultTileFactory tileFactory = new DefaultTileFactory(info);
        jXMapKit.setTileFactory(tileFactory);

        //location of Java
        //final GeoPosition gp = new GeoPosition(-7.502778, 111.263056);
        //location of my home
        
        var p1 = 40.7128;
        var p2 = -74.0060;
        final GeoPosition gp = new GeoPosition(p1, p2);
        

        final JToolTip tooltip = new JToolTip();
        //tooltip.add();
        tooltip.setTipText("Pizz hut\nPoor Service\n2/5 stars :(");
        tooltip.setComponent(jXMapKit.getMainMap());
        jXMapKit.getMainMap().add(tooltip);

        jXMapKit.setZoom(11);
        jXMapKit.setAddressLocation(gp);

        jXMapKit.getMainMap().addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) { 
                // ignore
            }

            @Override
            public void mouseMoved(MouseEvent e)
            {
                JXMapViewer map = jXMapKit.getMainMap();

                // convert to world bitmap
                Point2D worldPos = map.getTileFactory().geoToPixel(gp, map.getZoom());

                // convert to screen
                Rectangle rect = map.getViewportBounds();
                int sx = (int) worldPos.getX() - rect.x;
                int sy = (int) worldPos.getY() - rect.y;
                Point screenPos = new Point(sx, sy);

                // check if near the mouse
                if (screenPos.distance(e.getPoint()) < 20)
                {
                    screenPos.x -= tooltip.getWidth() / 2;

                    tooltip.setLocation(screenPos);
                    tooltip.setVisible(true);
                }
                else
                {
                    tooltip.setVisible(false);
                }
            }
        });

        // Display the viewer in a JFrame
        JFrame mapFrame = new JFrame("JXMapviewer2 Example 6");
        //mapFrame.setLayout(null);
        mapFrame.getContentPane().add(jXMapKit);
        mapFrame.setSize(800, 600);
        mapFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mapFrame.setVisible(true);
        
        BoxLayout layout = new BoxLayout(mapFrame, BoxLayout.Y_AXIS );
        Container panel1 = new Container();
        //panel
        //JTextField textField = new JTextField("This is a text field", 20);
        //textField.setSize(200, 40);
       
        //mapFrame.add(textField, BorderLayout.SOUTH);
        JButton button = new JButton("I am a button");
        mapFrame.add(button, BorderLayout.EAST);
        
        
        
       

    }
    

}
