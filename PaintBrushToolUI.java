package a8;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Hashtable;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class PaintBrushToolUI extends JPanel implements ChangeListener {

	private JSlider red_slider;
	private JSlider green_slider;
	private JSlider blue_slider;
	private JSlider brush_size_slider;
	private FrameView color_preview;
	private int brushSize;
	
	public PaintBrushToolUI() {
		setLayout(new GridLayout(0,1));
		
		JPanel color_chooser_panel = new JPanel();
		color_chooser_panel.setLayout(new BorderLayout());
		
		JPanel slider_panel = new JPanel();
		slider_panel.setLayout(new GridLayout(0,1));
		
		JPanel red_slider_panel = new JPanel();
		JLabel red_label = new JLabel("Red:");
		red_slider_panel.setLayout(new BorderLayout());
		red_slider_panel.add(red_label, BorderLayout.WEST);
		red_slider = new JSlider(0,100);
		red_slider.addChangeListener(this);
		red_slider_panel.add(red_slider, BorderLayout.CENTER);

		JPanel green_slider_panel = new JPanel();
		JLabel green_label = new JLabel("Green:");
		green_slider_panel.setLayout(new BorderLayout());
		green_slider_panel.add(green_label, BorderLayout.WEST);
		green_slider = new JSlider(0,100);
		green_slider.addChangeListener(this);
		green_slider_panel.add(green_slider, BorderLayout.CENTER);

		JPanel blue_slider_panel = new JPanel();
		JLabel blue_label = new JLabel("Blue: ");
		blue_slider_panel.setLayout(new BorderLayout());
		blue_slider_panel.add(blue_label, BorderLayout.WEST);
		blue_slider = new JSlider(0,100);
		blue_slider.addChangeListener(this);
		blue_slider_panel.add(blue_slider, BorderLayout.CENTER);
		
		JPanel brush_size_panel = new JPanel();
		JLabel brush_size_label = new JLabel("Brush Size: ");
		brush_size_panel.setLayout(new BorderLayout());
		brush_size_panel.add(brush_size_label, BorderLayout.WEST);
		brush_size_slider = new JSlider(1,25,5);
		Hashtable<Integer, JLabel> labelTable = new Hashtable<Integer, JLabel>();
		labelTable.put(new Integer(1), new JLabel("1"));
		labelTable.put(new Integer(5), new JLabel("5"));
		labelTable.put(new Integer(10), new JLabel("10"));
		labelTable.put(new Integer(15), new JLabel("15"));
		labelTable.put(new Integer(20), new JLabel("20"));
		labelTable.put(new Integer(25), new JLabel("25"));
		brush_size_slider.setLabelTable(labelTable);
		brush_size_slider.setPaintLabels(true);
		brush_size_slider.addChangeListener(this);
		brush_size_panel.add(brush_size_slider, BorderLayout.CENTER);

		// Assumes green label is widest and asks red and blue label
		// to be the same.
		Dimension d = green_label.getPreferredSize();
		red_label.setPreferredSize(d);
		blue_label.setPreferredSize(d);
		
		slider_panel.add(red_slider_panel);
		slider_panel.add(green_slider_panel);
		slider_panel.add(blue_slider_panel);
		slider_panel.add(brush_size_panel);

		color_chooser_panel.add(slider_panel, BorderLayout.CENTER);

		color_preview = new FrameView(new ColorFrame(50,50));
		color_chooser_panel.add(color_preview, BorderLayout.EAST);

		add(color_chooser_panel);
		
		stateChanged(null);
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		Pixel p = new ColorPixel(red_slider.getValue()/100.0,
				                 green_slider.getValue()/100.0,
				                 blue_slider.getValue()/100.0);
		Frame preview_frame = color_preview.getFrame();
		preview_frame.suspendNotifications();
		for (int x=0; x<preview_frame.getWidth(); x++) {
			for (int y=0; y<preview_frame.getHeight(); y++) {
				preview_frame.setPixel(x, y, p);
			}
		}
		
		preview_frame.resumeNotifications();
	}
	
	public Pixel getBrushColor() {
		return color_preview.getFrame().getPixel(0,0);
	}
	public int getBrushSize(){
		brushSize = brush_size_slider.getValue();
		return brushSize;
	}
}

