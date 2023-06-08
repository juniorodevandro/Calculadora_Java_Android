package com.calculadora.calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.util.Stack;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button numeroZero,
                   numeroUm,
                   numeroDois,
                   numeroTres,
                   numeroQuatro,
                   numeroCinco,
                   numeroSeis,
                   numeroSete,
                   numeroOito,
                   numeroNove,
                   soma,
                   subtracao,
                   divisao,
                   multiplicacao,
                   limpar,
                   ponto,
                   igual;

    private TextView txtExpressao,
                     txtResultado;

    private ImageView backspace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        IniciarComponentes();

        numeroZero.setOnClickListener(this);
        numeroUm.setOnClickListener(this);
        numeroDois.setOnClickListener(this);
        numeroTres.setOnClickListener(this);
        numeroQuatro.setOnClickListener(this);
        numeroCinco.setOnClickListener(this);
        numeroSeis.setOnClickListener(this);
        numeroSete.setOnClickListener(this);
        numeroOito.setOnClickListener(this);
        numeroNove.setOnClickListener(this);
        ponto.setOnClickListener(this);
        soma.setOnClickListener(this);
        subtracao.setOnClickListener(this);
        divisao.setOnClickListener(this);
        multiplicacao.setOnClickListener(this);

        limpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtExpressao.setText("");
                txtResultado.setText("");
            }
        });

        backspace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView expressao = findViewById(R.id.TextViewExpressao);
                String string = expressao.getText().toString();

                if (!string.isEmpty()){
                    int tamanho = string.length()-1;
                    String txtExpressao = string.substring(0, tamanho);
                    expressao.setText(txtExpressao);
                }
                txtResultado.setText("");
            }
        });

        igual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    double resultado = calcularExpressao(txtExpressao.getText().toString());
                    long longResult = (long) resultado;

                    if (resultado == (double) longResult) {
                        txtResultado.setText((CharSequence) String.valueOf(longResult));
                    } else {
                        txtResultado.setText((CharSequence) String.valueOf(resultado));
                    }
                }catch (Exception e){

                }
            }
        });
    }

    public static double calcularExpressao(String expressao) {
        Stack<Double> pilha = new Stack<>();

        String[] elementos = expressao.split(" ");

        for (String elemento : elementos) {
            if (isOperador(elemento)) {
                double segundoOperando = pilha.pop();
                double primeiroOperando = pilha.pop();
                double resultado = realizarOperacao(elemento, primeiroOperando, segundoOperando);
                pilha.push(resultado);
            } else {
                double numero = Double.parseDouble(elemento);
                pilha.push(numero);
            }
        }

        return pilha.pop();
    }

    private static double realizarOperacao(String operador, double primeiroOperando, double segundoOperando) {
        switch (operador) {
            case "+":
                return primeiroOperando + segundoOperando;
            case "-":
                return primeiroOperando - segundoOperando;
            case "*":
                return primeiroOperando * segundoOperando;
            case "/":
                return primeiroOperando / segundoOperando;
            default:
                throw new IllegalArgumentException("Operador inválido: " + operador);
        }
    }

    private static boolean isOperador(String elemento) {
        return elemento.equals("+") || elemento.equals("-") || elemento.equals("*") || elemento.equals("/");
    }

    private void IniciarComponentes(){
        numeroZero = findViewById(R.id.Button0);
        numeroUm = findViewById(R.id.Button1);
        numeroDois = findViewById(R.id.Button2);
        numeroTres = findViewById(R.id.Button3);
        numeroQuatro = findViewById(R.id.Button4);
        numeroCinco = findViewById(R.id.Button5);
        numeroSeis = findViewById(R.id.Button6);
        numeroSete = findViewById(R.id.Button7);
        numeroOito = findViewById(R.id.Button8);
        numeroNove = findViewById(R.id.Button9);

        ponto = findViewById(R.id.ButtonPonto);
        subtracao = findViewById(R.id.ButtonSubtracao);
        soma = findViewById(R.id.ButtonSoma);
        divisao = findViewById(R.id.ButtonDivisao);
        multiplicacao = findViewById(R.id.ButtonMultiplicacao);
        limpar = findViewById(R.id.ButtonLimpar);
        backspace = findViewById(R.id.Backspace);
        igual = findViewById(R.id.ButtonIgual);

        txtExpressao = findViewById(R.id.TextViewExpressao);
        txtResultado = findViewById(R.id.TextViewResultado);
    }

    public void AcrescentarExpressao(String prString, Boolean prLimparDados){
        if (txtResultado.getText().equals("")){
            txtExpressao.setText(" ");
        }

        if(prLimparDados){
            txtResultado.setText(" ");
            txtExpressao.append(prString);
        }else{
            txtExpressao.append(txtResultado.getText());
            txtExpressao.append(prString);
            txtResultado.setText(" ");
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.Button0) {
            AcrescentarExpressao("0", true);
        } else if (view.getId() == R.id.Button1) {
            AcrescentarExpressao("1", true);
        } else if (view.getId() == R.id.Button2) {
            AcrescentarExpressao("2", true);
        } else if (view.getId() == R.id.Button3) {
            AcrescentarExpressao("3", true);
        } else if (view.getId() == R.id.Button4) {
            AcrescentarExpressao("4", true);
        } else if (view.getId() == R.id.Button5) {
            AcrescentarExpressao("5", true);
        } else if (view.getId() == R.id.Button6) {
            AcrescentarExpressao("6", true);
        } else if (view.getId() == R.id.Button7) {
            AcrescentarExpressao("7", true);
        } else if (view.getId() == R.id.Button8) {
            AcrescentarExpressao("8", true);
        } else if (view.getId() == R.id.Button9) {
            AcrescentarExpressao("9", true);
        } else if (view.getId() == R.id.ButtonPonto) {
            AcrescentarExpressao(".", true);
        } else if (view.getId() == R.id.ButtonSoma) {
            AcrescentarExpressao("+", false);
        } else if (view.getId() == R.id.ButtonDivisao) {
            AcrescentarExpressao("/", false);
        } else if (view.getId() == R.id.ButtonMultiplicacao) {
            AcrescentarExpressao("*", false);
        } else if (view.getId() == R.id.ButtonSubtracao) {
            AcrescentarExpressao("-", false);
        }
    }
}