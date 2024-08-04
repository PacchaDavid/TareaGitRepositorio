package interfazGraficaArbolBinario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfazGraficaArbol extends JFrame {
    private ArbolBinario arbol;
    private JTextField textoInsertar;
    private JTextField textoEliminar;
    private JButton botonInsertar;
    private JButton botonEliminar;
    private JPanel panelDibujo;

    public InterfazGraficaArbol() {
        arbol = new ArbolBinario();

        setTitle("Arbol de Búsqueda Binaria");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        textoInsertar = new JTextField(10);
        botonInsertar = new JButton("Insertar");
        botonInsertar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int valor = Integer.parseInt(textoInsertar.getText().trim());
                    arbol.insertar(valor);
                    textoInsertar.setText("");
                    panelDibujo.repaint();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(InterfazGraficaArbol.this,
                            "Ingrese un valor entero válido",
                            "Entrada no válida",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        textoEliminar = new JTextField(10);
        botonEliminar = new JButton("Eliminar");
        botonEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int valor = Integer.parseInt(textoEliminar.getText().trim());
                    if (arbol.eliminar(valor)) {
                        textoEliminar.setText("");
                        panelDibujo.repaint();
                    } else {
                        JOptionPane.showMessageDialog(InterfazGraficaArbol.this,
                                "Valor no encontrado",
                                "Nodo no encontrado",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(InterfazGraficaArbol.this,
                            "Ingrese un valor entero válido",
                            "Entrada no válida",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        panelDibujo = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                
                if (arbol.getRaiz() != null) {
                    drawTree(g, getWidth() / 2, 30, arbol.getRaiz(), getWidth() / 4);
                }
            }
        };
        panelDibujo.setPreferredSize(new Dimension(800, 600));

        JPanel panelInsertar = new JPanel();
        panelInsertar.add(new JLabel("Insertar valor:"));
        panelInsertar.add(textoInsertar);
        panelInsertar.add(botonInsertar);

        JPanel deletePanel = new JPanel();
        deletePanel.add(new JLabel("Eliminar valor:"));
        deletePanel.add(textoEliminar);
        deletePanel.add(botonEliminar);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(2, 1));
        inputPanel.add(panelInsertar);
        inputPanel.add(deletePanel);

        add(inputPanel, BorderLayout.NORTH);
        add(panelDibujo, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null); 
    }

    private void drawTree(Graphics g, int x, int y, ArbolBinario.NodoArbol nodo, int xOffset) {
        g.setColor(Color.black);
        g.drawString(String.valueOf(nodo.value), x, y);
        if (nodo.izquierda != null) {
            
            int newX = x - xOffset;
            int newY = y + 50;
            g.drawLine(x, y, newX, newY);
            drawTree(g, newX, newY, nodo.izquierda, xOffset / 2);
        }
        if (nodo.derecha != null) {
    
            int newX = x + xOffset;
            int newY = y + 50;
            g.drawLine(x, y, newX, newY);
            drawTree(g, newX, newY, nodo.derecha, xOffset / 2);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new InterfazGraficaArbol().setVisible(true);
            }
        });
    }

    private class ArbolBinario {
        private NodoArbol raiz;

        public ArbolBinario() {
            raiz = null;
        }

        public NodoArbol getRaiz() {
            return raiz;
        }

        public void insertar(int valor) {
            raiz = insertarRecursivo(raiz, valor);
        }

        private NodoArbol insertarRecursivo(NodoArbol raiz, int valor) {
            if (raiz == null) {
                raiz = new NodoArbol(valor);
                return raiz;
            }
            if (valor < raiz.value) {
                raiz.izquierda = insertarRecursivo(raiz.izquierda, valor);
            } else if (valor > raiz.value) {
                raiz.derecha = insertarRecursivo(raiz.derecha, valor);
            }
            return raiz;
        }

        public boolean eliminar(int valor) {
            NodoArbol padre = null;
            NodoArbol actual = raiz;
            boolean hijoIzquierdo = false;


            while (actual != null && actual.value != valor) {
                padre = actual;
                if (valor < actual.value) {
                    actual = actual.izquierda;
                    hijoIzquierdo = true;
                } else {
                    actual = actual.derecha;
                    hijoIzquierdo = false;
                }
            }


            if (actual == null) {
                return false;
            }

            if (actual.izquierda == null && actual.derecha == null) {
                if (actual == raiz) {
                    raiz = null;
                } else if (hijoIzquierdo) {
                    padre.izquierda = null;
                } else {
                    padre.derecha = null;
                }
            }

            else if (actual.derecha == null) {
                if (actual == raiz) {
                    raiz = actual.izquierda;
                } else if (hijoIzquierdo) {
                    padre.izquierda = actual.izquierda;
                } else {
                    padre.derecha = actual.izquierda;
                }
            } else if (actual.izquierda == null) {
                if (actual == raiz) {
                    raiz = actual.derecha;
                } else if (hijoIzquierdo) {
                    padre.izquierda = actual.derecha;
                } else {
                    padre.derecha = actual.derecha;
                }
            }

            else {

                NodoArbol siguiente = getSiguiente(actual);

  
                if (actual == raiz) {
                    raiz = siguiente;
                } else if (hijoIzquierdo) {
                    padre.izquierda = siguiente;
                } else {
                    padre.derecha = siguiente;
                }

                siguiente.izquierda = actual.izquierda;
            }

            return true;
        }

        private NodoArbol getSiguiente(NodoArbol nodo) {
            NodoArbol padreSiguiente = nodo;
            NodoArbol siguiente = nodo;
            NodoArbol actual = nodo.derecha;

            while (actual != null) {
                padreSiguiente = siguiente;
                siguiente = actual;
                actual = actual.izquierda;
            }

            if (siguiente != nodo.derecha) {
                padreSiguiente.izquierda = siguiente.derecha;
                siguiente.derecha = nodo.derecha;
            }

            return siguiente;
        }


        private class NodoArbol {
            int value;
            NodoArbol izquierda, derecha;

            public NodoArbol(int value) {
                this.value = value;
                izquierda = derecha = null;
            }
        }
    }
}
