
package mapeamentopolitico;

import java.util.ArrayList;
import java.util.Objects;
import java.io.Serializable;

public class Estrutura implements Serializable{
  
    private static final long serialVersionUID = 1L;
    public String nome;
    public String comunidade;
    public String post;
    public int freq;
    public String posicao;
    
    Estrutura () {
    }

    public Estrutura(String nome, String comunidade, String post, int freq, String posicao) {
        this.nome = nome;
        this.comunidade = comunidade;
        this.post = post;
        this.freq = freq;
        this.posicao = posicao;
    }
  
    public String getNome() {
        return nome;
    }
	
    public void setNome(String nome) {
	this.nome = nome;
    }
   
    public String getComunidade() {
        return comunidade;
    }
	
    public void setComunidade(String comunidade) {
	this.comunidade = comunidade;
    }
    
    public String getPost() {
        return post;
    }
	
    public void setPost(String post) {
	this.post = post;
    }
    
    public int getFreq() {
        return freq;
    }
    
    public void addFreq(int freq) {
        this.freq = freq++;
    }
	
    public void setFreq(int freq) {
	this.freq = freq;
    }
    
    public String getPosicao() {
        return posicao;
    }
	
    public void setPosicao(String posicao) {
	this.posicao = posicao;
    }
}
