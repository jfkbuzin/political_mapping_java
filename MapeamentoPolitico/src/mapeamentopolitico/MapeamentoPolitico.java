package mapeamentopolitico;

import java.io.*;
import java.util.*;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class MapeamentoPolitico {

    public static void main(String[] args) throws IOException {
       
        LerArquivos c = new LerArquivos();
        SalvaD b = new SalvaD();
        ArrayList<Estrutura> com;
        
        int n = 0;
        
        
        JFileChooser fileChooser = new JFileChooser();
        
        do{
                Object[] possibleValues = { "Inserir Usuário", "Fazer Pesquisas", "Listar Usuarios",  
                    "Listar Paginas que são usadas na classificação da posição politica", 
                    "Adicionar Paginas que são usadas na classificação da posição politica" ,"Sair" };
                Object selectedValue = JOptionPane.showInputDialog(null,"Escolha um", "Input"
                          ,JOptionPane.INFORMATION_MESSAGE, null,possibleValues, possibleValues[0]);  
                  
                if(selectedValue == "Inserir Usuário")
                    n = 1;
                else if(selectedValue == "Fazer Pesquisas")
                    n = 2;
                else if(selectedValue == "Listar Usuarios")
                    n = 3;
                else if(selectedValue == "Listar Paginas que são usadas na classificação da posição politica")
                    n = 4;
                else if(selectedValue == "Adicionar Paginas que são usadas na classificação da posição politica")
                    n = 5;                
                else
                    n=0;
                  

                  //n = in.nextInt();

                      switch (n){

                          case 1:
                                int i = fileChooser.showOpenDialog(null);
        
                                if (i == JFileChooser.APPROVE_OPTION) {
                                    File file = fileChooser.getSelectedFile();


                                    c.LeituraHTM(file);
                                    
                                    //JOptionPane.showMessageDialog(null, "Ao virar txt");

                                    com = c.LeituraTXT();
                                    
                                    //JOptionPane.showMessageDialog(null, "Antes do Heapsort");
                                    
                                    Heapsort heap = new Heapsort();
                                    
                                    //JOptionPane.showMessageDialog(null, "Depois do Heapsort");

                                    
                                    com = heap.heapSort(com);


                                    b.ArqB(com);
                                    com.clear();


                                }
                                else {
                                    JOptionPane.showMessageDialog(null, "Nenhum Arquivo foi escolhido!");
                                }
                               break;
                          case 2:
                                b.LerTudo();
                                break; 
                          case 3:
                                File curDir = new File(".");
                                b.listaNomes(curDir);   
                                break;
                          case 4:
                                c.listaComTXT();   
                                break;
                          case 5:
                                c.addComTXT();
                                break;
                          case 0:
                                JOptionPane.showMessageDialog(null, "Programa Encerrado");
                                break;
                          default:      
                                JOptionPane.showMessageDialog(null, "Opção Inválida");
                      }
              }while (n != 0);
        
    }
}
