package interfaz;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import calculadora.Ingrediente;
import calculadora.Receta;
import javax.swing.JComboBox;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;

public class CalculadoraInterface {
	private Receta receta;
	private String string;
	private static int heightListaIngrediente;
	private static int heightPanelChiquito;

	private JFrame frame;
	private ImageIcon icono;
	private JPanel panel;
	private JPanel panelSuperiorDerecho;
	private JPanel panel_inferior_derecho;
	private JButton btnAgregarIngred;
	private JButton btnCalcular;
	private JButton btnEliminar;
	private JLabel labelCostoProducto;
	private JLabel lblIngrediente;
	private JLabel lblCantPaquete;
	private JLabel lblPrecio;
	private JLabel lblCantUsada;
	private JLabel lblProducto_CantidadUsada;
	private JLabel lblGastosOperacionales;
	private JLabel lblPorcentaje;
	private JLabel lblAdvertencia;
	private JLabel lblManoObra;
	private JLabel lblCantidadObtenida;
	private JLabel lblcantidadVendida;
	private JLabel listaIngrediente;
	private JLabel lblResultados_der;
	private JLabel lblResultados_izq;
	private JLabel lbl_x;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JComboBox<Integer> comboBox;
	private JComboBox<Integer> comboBox_1;
	private JScrollPane scroll;
	private JSeparator separator;
	private JLabel lblElimiarIngrediente;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CalculadoraInterface window = new CalculadoraInterface();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CalculadoraInterface() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		this.receta = new Receta();
		this.string = "<html> \n";
		heightListaIngrediente = 28;
		heightPanelChiquito = 204;

		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		crearFrame();
		crearPanel();
		crearLabels();
		jTextIngredientes();
		btnAgregarIngrediente();
		btnCalcular();
		btnEliminar();
		crearComboBox();

	}

	private void crearFrame() {
		icono = new ImageIcon("./bin/iconoCalculadora.png");
		frame = new JFrame("Calculador de costos");
		frame.setBounds(250, 140, 1117, 560);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setIconImage(icono.getImage());
		frame.setVisible(true);
	}

	private void crearPanel() {

		panel = new JPanel();
		panel.setForeground(Color.BLACK);
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(10, 10, 604, 497);
		panel.setLayout(null);
		frame.getContentPane().add(panel);

		panelSuperiorDerecho = new JPanel();
		panelSuperiorDerecho.setBounds(497, 10, 379, 194);
		panelSuperiorDerecho.setLayout(null);
		panelSuperiorDerecho.setPreferredSize(new Dimension(360, 204));
		// panel.add(panelChiquito);

		panel_inferior_derecho = new JPanel();
		panel_inferior_derecho.setBounds(580, 269, 490, 184);
		panel.add(panel_inferior_derecho);
		panel_inferior_derecho.setLayout(null);

		scroll = new JScrollPane(panelSuperiorDerecho, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setBounds(580, 10, 490, 211);
		scroll.setViewportView(panelSuperiorDerecho);
		panel.add(scroll);

	}

	private void btnAgregarIngrediente() {
		btnAgregarIngred = new JButton("Agregar ingrediente");
		btnAgregarIngred.setFont(new Font("Arial", Font.PLAIN, 15));
		btnAgregarIngred.setBounds(159, 128, 185, 38);
		panel.add(btnAgregarIngred);

		btnAgregarIngred.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if (camposVacios()) {
					mostrarAdvertencia();

				} else {
					lblAdvertencia.setVisible(false);
					btnCalcular.setEnabled(true);
					receta.agregarIngrediente(
							new Ingrediente(getIngrediente(), getPrecio(), getCantXPaquete(), getCantUsada()));
					mostrarIngrediente();
					sacarDatos();
				}
			}

		});

	}

	private void btnCalcular() {
		btnCalcular = new JButton("Calcular");
		btnCalcular.setEnabled(false);
		btnCalcular.setFont(new Font("Arial", Font.BOLD, 15));
		btnCalcular.setBounds(159, 443, 198, 38);
		panel.add(btnCalcular);

		btnCalcular.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (textField_6.getText().isEmpty() || textField_5.getText().isEmpty()) {
					mostrarAdvertencia();

				} else if (textField_6.getText().equals("0") || textField_5.getText().equals("0")) {
					mostrarAdvertencia();

				}

				else {
					setPorcentaje();
					setCostoManoObra();
					setCantidadVendida();
					setCantidadObtenida();
					receta.calcularCostos();
					mostrarResultados();

				}

			}

		});
	}

	private void btnEliminar() {
		btnEliminar = new JButton("X");
		btnEliminar.setForeground(Color.RED);
		btnEliminar.setFont(new Font("Arial", Font.BOLD, 11));
		btnEliminar.setBounds(234, 192, 39, 29);
		panel.add(btnEliminar);

		btnEliminar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (textField_7.getText().isEmpty()) {
					mostrarAdvertencia();

				}

				else {

					eliminarIngrediente();
					textField_7.setText("");

				}

			}

		});
	}

	private void sacarDatos() {
		textField_1.setText("");
		textField_2.setText("");
		textField_3.setText("");
		textField_4.setText("");

	}

	private void crearComboBox() {
		comboBox = new JComboBox<Integer>();
		comboBox.setFont(new Font("Consolas", Font.BOLD, 13));
		comboBox.setBounds(231, 281, 42, 21);
		comboBox.setModel(new DefaultComboBoxModel<Integer>(new Integer[] { 30, 40, 50 }));
		panel.add(comboBox);

		comboBox_1 = new JComboBox<Integer>();
		comboBox_1.setFont(new Font("Consolas", Font.BOLD, 13));
		comboBox_1.setBounds(231, 315, 42, 21);
		comboBox_1.setModel(new DefaultComboBoxModel<Integer>(new Integer[] { 2, 3, 4, 5 }));
		panel.add(comboBox_1);

		separator = new JSeparator();
		separator.setBackground(Color.BLACK);
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(512, 10, 7, 503);
		panel.add(separator);
		

	}

	private void jTextIngredientes() {
		textField_1 = new JTextField();
		textField_1.setBounds(10, 86, 96, 19);
		panel.add(textField_1);
		textField_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {

				if (!Character.isLetter(e.getKeyChar()) && !(e.getKeyChar() == KeyEvent.VK_SPACE)) {
					e.consume();
				}
			}
		});

		textField_2 = new JTextField();
		textField_2.setBounds(141, 86, 96, 19);
		panel.add(textField_2);
		textField_2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {

				if (!Character.isDigit(e.getKeyChar()) && !(e.getKeyChar() == KeyEvent.VK_PERIOD)) {
					e.consume();
				}

			}
		});

		textField_3 = new JTextField();
		textField_3.setBounds(261, 86, 96, 19);
		panel.add(textField_3);
		textField_3.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {

				if (!Character.isDigit(e.getKeyChar()) && !(e.getKeyChar() == KeyEvent.VK_PERIOD)) {
					e.consume();
				}

			}
		});

		textField_4 = new JTextField();
		textField_4.setBounds(387, 86, 96, 19);
		panel.add(textField_4);
		textField_4.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {

				if (!Character.isDigit(e.getKeyChar()) && !(e.getKeyChar() == KeyEvent.VK_PERIOD)) {
					e.consume();
				}

			}
		});

		textField_5 = new JTextField();
		textField_5.setBounds(231, 353, 42, 19);
		panel.add(textField_5);
		textField_5.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {

				if (!Character.isDigit(e.getKeyChar()) && !(e.getKeyChar() == KeyEvent.VK_PERIOD)) {
					e.consume();
				}

			}
		});

		textField_6 = new JTextField();
		textField_6.setBounds(231, 400, 42, 19);
		panel.add(textField_6);
		textField_6.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {

				if (!Character.isDigit(e.getKeyChar()) && !(e.getKeyChar() == KeyEvent.VK_PERIOD)) {
					e.consume();
				}

			}
		});

		textField_7 = new JTextField();
		textField_7.setBounds(117, 197, 96, 19);
		panel.add(textField_7);
		textField_7.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {

				if (!Character.isLetter(e.getKeyChar()) && !(e.getKeyChar() == KeyEvent.VK_SPACE)) {
					e.consume();
				}
			}
		});

	}

	private void crearLabels() {
		labelCostoProducto = new JLabel("*Costo del Producto");
		labelCostoProducto.setBounds(10, 10, 227, 38);
		labelCostoProducto.setFont(new Font("Times New Roman", Font.BOLD, 20));
		panel.add(labelCostoProducto);

		lblIngrediente = new JLabel("Ingrediente:");
		lblIngrediente.setFont(new Font("Arial", Font.PLAIN, 15));
		lblIngrediente.setBounds(10, 47, 96, 43);
		panel.add(lblIngrediente);

		lblCantPaquete = new JLabel("<html>Cantidad x<p> paquete:");
		lblCantPaquete.setFont(new Font("Arial", Font.PLAIN, 15));
		lblCantPaquete.setBounds(261, 47, 96, 43);
		panel.add(lblCantPaquete);

		lblPrecio = new JLabel("<html>Precio del<p> paquete:");
		lblPrecio.setFont(new Font("Arial", Font.PLAIN, 15));
		lblPrecio.setBounds(141, 47, 96, 43);
		panel.add(lblPrecio);

		lblCantUsada = new JLabel("<html>Cantidad<p> usada:");
		lblCantUsada.setFont(new Font("Arial", Font.PLAIN, 15));
		lblCantUsada.setBounds(387, 47, 96, 43);
		panel.add(lblCantUsada);

		lblProducto_CantidadUsada = new JLabel("Producto : Precio / Cantidad x Paq. x Cantidad usada:");
		lblProducto_CantidadUsada.setForeground(Color.MAGENTA);
		lblProducto_CantidadUsada.setFont(new Font("Arial", Font.BOLD, 17));
		lblProducto_CantidadUsada.setBounds(10, 0, 456, 24);
		panelSuperiorDerecho.add(lblProducto_CantidadUsada);

		lblGastosOperacionales = new JLabel("*Gastos operacionales:");
		lblGastosOperacionales.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblGastosOperacionales.setBounds(10, 273, 214, 29);
		panel.add(lblGastosOperacionales);

		lblPorcentaje = new JLabel("%");
		lblPorcentaje.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblPorcentaje.setBounds(283, 278, 21, 20);
		panel.add(lblPorcentaje);

		lbl_x = new JLabel("x");
		lbl_x.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lbl_x.setBounds(217, 312, 7, 20);
		panel.add(lbl_x);

		lblManoObra = new JLabel("*Mano de obra:");
		lblManoObra.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblManoObra.setBounds(10, 308, 197, 29);
		panel.add(lblManoObra);

		lblCantidadObtenida = new JLabel("*Cantidad obtenida:");
		lblCantidadObtenida.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblCantidadObtenida.setBounds(10, 347, 197, 29);
		panel.add(lblCantidadObtenida);

		lblcantidadVendida = new JLabel("*Cantidad vendida:");
		lblcantidadVendida.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblcantidadVendida.setBounds(10, 394, 203, 29);
		panel.add(lblcantidadVendida);
		
		lblElimiarIngrediente = new JLabel("<html>Eliminar<p> ingrediente:");
		lblElimiarIngrediente.setFont(new Font("Arial", Font.PLAIN, 15));
		lblElimiarIngrediente.setBounds(10, 191, 96, 43);
		panel.add(lblElimiarIngrediente);


		lblAdvertencia = new JLabel("!");
		lblAdvertencia.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdvertencia.setForeground(Color.RED);
		lblAdvertencia.setFont(new Font("Calibri", Font.PLAIN, 40));
		lblAdvertencia.setBounds(329, 314, 47, 62);
		lblAdvertencia.setVisible(false);
		panel.add(lblAdvertencia);

		listaIngrediente = new JLabel();
		listaIngrediente.setVerticalAlignment(SwingConstants.TOP);
		listaIngrediente.setBounds(10, 28, 456, 28);
		listaIngrediente.setFont(new Font("Arial", Font.PLAIN, 15));
		panelSuperiorDerecho.add(listaIngrediente);

		lblResultados_der = new JLabel("");
		lblResultados_der.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblResultados_der.setVerticalAlignment(SwingConstants.TOP);
		lblResultados_der.setBounds(10, 10, 241, 164);
		panel_inferior_derecho.add(lblResultados_der);

		lblResultados_izq = new JLabel("");
		lblResultados_izq.setVerticalAlignment(SwingConstants.TOP);
		lblResultados_izq.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblResultados_izq.setBounds(261, 10, 219, 164);
		panel_inferior_derecho.add(lblResultados_izq);

	}

	private void setPorcentaje() {

		if (comboBox.getSelectedIndex() == 0)
			receta.setPorcentaje(30);
		else if (comboBox.getSelectedIndex() == 1)
			receta.setPorcentaje(40);
		else
			receta.setPorcentaje(50);
	}

	private void setCostoManoObra() {
		if (comboBox_1.getSelectedIndex() == 0)
			receta.setCostoManoObra(2);
		else if (comboBox_1.getSelectedIndex() == 1)
			receta.setCostoManoObra(3);
		else if (comboBox_1.getSelectedIndex() == 2)
			receta.setCostoManoObra(4);
		else
			receta.setCostoManoObra(5);
	}

	private void setCantidadObtenida() {
		receta.setCantidadObtenida(Double.parseDouble(textField_5.getText()));
	}

	private void setCantidadVendida() {
		receta.setCantidadVendida(Double.parseDouble(textField_6.getText()));
	}

	private void mostrarIngrediente() {

		if (listaIngrediente.getHeight() > 265) {
			heightPanelChiquito += 25;
			panelSuperiorDerecho.setPreferredSize(new Dimension(360, heightPanelChiquito));
		}
		
		string = "<html>" + receta.getInfo();
		listaIngrediente.setText(string);

		heightListaIngrediente += 25;
		listaIngrediente.setBounds(10, 28, 350, heightListaIngrediente);

	}

	private void eliminarIngrediente() {
		if (listaIngrediente.getHeight() > 265) {
			heightPanelChiquito -= 25;
			panelSuperiorDerecho.setPreferredSize(new Dimension(360, heightPanelChiquito));
		}
		
		receta.eliminarIngrediente(textField_7.getText().toLowerCase());

		string = "<html>" + receta.getInfo();
		listaIngrediente.setText(string);

		heightListaIngrediente -= 25;
		listaIngrediente.setBounds(10, 28, 350, heightListaIngrediente);
	}

	private void mostrarResultados() {
		lblResultados_der.setText("<html>Costo productos: $" + receta.getCostoProductos() + "<p>" + "Gastos op.: $"
				+ receta.getCostoGastosOperacionales() + "<p>" + "Ganancias: x" + receta.getCostoManoObra() + "<p><p> "
				+ "SUBTOTAL: $" + receta.getPrecioCostos() + "<p>" + "TOTAL : $" + receta.getPrecioFinal()

		);

		lblResultados_izq.setText("<html>Precio Unitario: x" + receta.getPrecioUnitario() + "<p>" + "Cantidad vendida: x"
				+ receta.getCantidadVendida() + "<p>");
	}

	private void mostrarAdvertencia() {
		lblAdvertencia.setVisible(true);
	}
	
	private boolean camposVacios() {
		return textField_1.getText().isEmpty() || textField_2.getText().isEmpty()
				|| textField_3.getText().isEmpty() || textField_4.getText().isEmpty();
		
	}

	private String getIngrediente() {

		return textField_1.getText().toLowerCase();
	}

	private double getPrecio() {
		return Double.parseDouble(textField_2.getText());
	}

	private double getCantXPaquete() {
		return Double.parseDouble(textField_3.getText());
	}

	private double getCantUsada() {
		return Double.parseDouble(textField_4.getText());
	}
}
