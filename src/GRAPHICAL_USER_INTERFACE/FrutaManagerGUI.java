package GRAPHICAL_USER_INTERFACE;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

// Classe publica FrutaManager GUI
public class FrutaManagerGUI {
	
    // Variáveis: uma ArrayList para armazenar frutas e um modelo para a lista visual
    private ArrayList<String> frutas;
    private DefaultListModel<String> listModel;
    private JList<String> list;

    // Construtor da classe FrutaManagerGUI onde a interface é configurada
    public FrutaManagerGUI() {
    	
        // Inicialização do ArrayList e do modelo da lista
        frutas = new ArrayList<>();
        listModel = new DefaultListModel<>();

        // Criando a janela principal com título "Gerenciador de Frutas"
        JFrame frame = new JFrame("Gerenciador de Frutas");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 300);
        frame.setLayout(new BorderLayout());

        // Criando o painel superior e adicionando um campo de texto e botões para interagir com as frutas
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        // Campo de texto para digitar o nome da fruta
        JTextField frutaField = new JTextField(15);
        panel.add(frutaField);
        
        // Botão para adicionar uma nova fruta
        JButton addButton = new JButton("Adicionar"); 
        panel.add(addButton);

        // Botão para modificar uma fruta existente
        JButton modifyButton = new JButton("Modificar");
        
        // Inicialmente desativado
        modifyButton.setEnabled(false);
        panel.add(modifyButton);
        
        // Botão para remover uma fruta
        JButton removeButton = new JButton("Remover");
        // Inicialmente desativado
        removeButton.setEnabled(false);
        panel.add(removeButton);

        // Adicionando o painel ao topo do frame
        frame.add(panel, BorderLayout.NORTH);

        // Configurando a lista visual para exibir as frutas e permitindo a seleção de apenas um item por vez
        list = new JList<>(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(list);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Botão para listar todas as frutas
        JButton listButton = new JButton("Listar Frutas");
        frame.add(listButton, BorderLayout.SOUTH);

        // Adiciona uma fruta à lista quando o botão "Adicionar" é clicado
        addButton.addActionListener(new ActionListener() {
            
        	@Override
            public void actionPerformed(ActionEvent e) {
        		// Captura o texto digitado
                String novaFruta = frutaField.getText();
                
                // Verifica se o campo não está vazio
                if (!novaFruta.isEmpty()) { 
                	// Adiciona a fruta ao ArrayList
                    frutas.add(novaFruta); 
                 	// Adiciona a fruta à lista visual
                    listModel.addElement(novaFruta); 
                 	// Limpa o campo de texto
                    frutaField.setText(""); 
                 	// Mostra uma mensagem de confirmação	
                    JOptionPane.showMessageDialog(frame, novaFruta + " foi adicionada."); 
                }
            }
        });

        // Modifica a fruta selecionada na lista quando o botão "Modificar" é clicado
        modifyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	 // Captura o índice do item selecionado
                int selectedIndex = list.getSelectedIndex();
                // Verifica se algum item está selecionado
                if (selectedIndex != -1) { 
                    String frutaSelecionada = listModel.getElementAt(selectedIndex);
                    // Abre um diálogo para o usuário digitar o novo nome da fruta
                    String novaFruta = JOptionPane.showInputDialog(frame, "Modificar " + frutaSelecionada + " para:", frutaSelecionada);
                    // Verifica se o usuário digitou algo
                    if (novaFruta != null && !novaFruta.isEmpty()) {
                    	// Atualiza o nome no ArrayList
                        frutas.set(selectedIndex, novaFruta); 
                        // Atualiza o nome na lista visual
                        listModel.set(selectedIndex, novaFruta); 
                        JOptionPane.showMessageDialog(frame, "Fruta " + frutaSelecionada + " foi modificada para " + novaFruta + ".");
                    }
                } else {
                	// Mostra uma mensagem se nenhuma fruta estiver selecionada
                    JOptionPane.showMessageDialog(frame, "Selecione uma fruta para modificar."); 
                }
            }
        });

        // Remove a fruta selecionada quando o botão "Remover" é clicado
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	// Captura o índice do item selecionado
                int selectedIndex = list.getSelectedIndex(); 
                if (selectedIndex != -1) {
                	 // Remove a fruta do ArrayList
                    String frutaRemovida = frutas.remove(selectedIndex);
                    // Remove a fruta da lista visual
                    listModel.remove(selectedIndex);
                    JOptionPane.showMessageDialog(frame, frutaRemovida + " foi removida.");
                } else {
                	// Mensagem caso nenhuma fruta esteja selecionada
                    JOptionPane.showMessageDialog(frame, "Selecione uma fruta para remover."); 
                }
            }
        });

        // Mostra todas as frutas armazenadas quando o botão "Listar Frutas" é clicado
        listButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (frutas.isEmpty()) {
                	// Mensagem se a lista estiver vazia
                    JOptionPane.showMessageDialog(frame, "Nenhuma fruta na lista."); 
                } else {
                	// Exibe todas as frutas no ArrayList
                    JOptionPane.showMessageDialog(frame, "Frutas: " + frutas); 
                }
            }
        });

        // Ouvinte para a lista que ativa/desativa os botões "Modificar" e "Remover" conforme seleção
        list.addListSelectionListener(e -> {
            boolean selectionExists = !list.isSelectionEmpty();
            removeButton.setEnabled(selectionExists);
            modifyButton.setEnabled(selectionExists);
        });

        // Tornando o frame visível
        frame.setVisible(true);
    }

    // Método principal que inicia a aplicação
    public static void main(String[] args) {
        // Cria a interface em uma thread separada
        SwingUtilities.invokeLater(() -> new FrutaManagerGUI());
    }
}
