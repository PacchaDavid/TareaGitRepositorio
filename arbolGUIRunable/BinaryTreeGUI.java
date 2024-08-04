import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BinaryTreeGUI extends JFrame {
    private ArbolBinario arbol;
    private JTextField textoInsertar;
    private JTextField textoEliminar;
    private JButton botonInsertar;
    private JButton botonEliminar;
    private JPanel panelDibujo;

    public BinaryTreeGUI() {
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
                    arbol.insert(valor);
                    textoInsertar.setText("");
                    panelDibujo.repaint();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(BinaryTreeGUI.this,
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
                        JOptionPane.showMessageDialog(BinaryTreeGUI.this,
                                "Valor no encontrado",
                                "Nodo no encontrado",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(BinaryTreeGUI.this,
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
                new BinaryTreeGUI().setVisible(true);
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

        public void insert(int valor) {
            raiz = insertRec(raiz, valor);
        }

        private NodoArbol insertRec(NodoArbol raiz, int valor) {
            if (raiz == null) {
                raiz = new NodoArbol(valor);
                return raiz;
            }
            if (valor < raiz.value) {
                raiz.izquierda = insertRec(raiz.izquierda, valor);
            } else if (valor > raiz.value) {
                raiz.derecha = insertRec(raiz.derecha, valor);
            }
            return raiz;
        }

        public boolean eliminar(int valor) {
            NodoArbol parent = null;
            NodoArbol current = raiz;
            boolean isLeftChild = false;


            while (current != null && current.value != valor) {
                parent = current;
                if (valor < current.value) {
                    current = current.izquierda;
                    isLeftChild = true;
                } else {
                    current = current.derecha;
                    isLeftChild = false;
                }
            }


            if (current == null) {
                return false;
            }

            if (current.izquierda == null && current.derecha == null) {
                if (current == raiz) {
                    raiz = null;
                } else if (isLeftChild) {
                    parent.izquierda = null;
                } else {
                    parent.derecha = null;
                }
            }

            else if (current.derecha == null) {
                if (current == raiz) {
                    raiz = current.izquierda;
                } else if (isLeftChild) {
                    parent.izquierda = current.izquierda;
                } else {
                    parent.derecha = current.izquierda;
                }
            } else if (current.izquierda == null) {
                if (current == raiz) {
                    raiz = current.derecha;
                } else if (isLeftChild) {
                    parent.izquierda = current.derecha;
                } else {
                    parent.derecha = current.derecha;
                }
            }

            else {

                NodoArbol siguiente = getSiguiente(current);

  
                if (current == raiz) {
                    raiz = siguiente;
                } else if (isLeftChild) {
                    parent.izquierda = siguiente;
                } else {
                    parent.derecha = siguiente;
                }

                siguiente.izquierda = current.izquierda;
            }

            return true;
        }

        private NodoArbol getSiguiente(NodoArbol nodo) {
            NodoArbol successorParent = nodo;
            NodoArbol siguiente = nodo;
            NodoArbol current = nodo.derecha;

            while (current != null) {
                successorParent = siguiente;
                siguiente = current;
                current = current.izquierda;
            }

            if (siguiente != nodo.derecha) {
                successorParent.izquierda = siguiente.derecha;
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
