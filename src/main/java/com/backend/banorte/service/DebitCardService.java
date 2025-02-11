package com.backend.banorte.service;

import com.backend.banorte.domain.dto.AccountDto;
import com.backend.banorte.domain.dto.CardDto;
import com.backend.banorte.domain.request.GetUserCardListRequest;
import com.backend.banorte.ports.DebitCardJpaPort;
import com.backend.banorte.ports.DebitCardServicePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import java.util.List;




@Service
public class DebitCardService implements DebitCardServicePort {


    @Autowired
    private DebitCardJpaPort debitCardJpaPort;




    @Override
    public ResponseEntity<List<CardDto>> getCardList(GetUserCardListRequest request) {


        List<CardDto> lista = debitCardJpaPort.getCardList(request.getAccountNumber());
        CardDto elementoLista = lista.get(0);

        showCardsReport(lista);



        CardDto getCard = new CardDto();
        getCard.setAccountNumber("12312412");
        getCard.setCardNumber("1234567890123456");
        getCard.setCardType("MASTERCARD");
        getCard.setExpireDate("12/24");
        getCard.setCvv("344");

        List<CardDto> cardDtoList =new ArrayList<>();
        CardDto card = new CardDto();
        card.setAccountNumber("100000000001");
        card.setCardNumber("1000000000000001");
        card.setCardType("MASTERCARD");
        card.setExpireDate("12/27");
        card.setCvv("344");
        CardDto card1 = new CardDto();
        card1.setAccountNumber("100000000002");
        card1.setCardNumber("1000000000000002");
        card1.setCardType("VISA");
        card1.setExpireDate("12/27");
        card1.setCvv("344");
        CardDto card2 = new CardDto();
        card2.setAccountNumber("100000000003");
        card2.setCardNumber("1000000000000003");
        card2.setCardType("VISA");
        card2.setExpireDate("12/28");
        card2.setCvv("344");

        cardDtoList.add(card);
        cardDtoList.add(card1);
        cardDtoList.add(card2);
        cardDtoList.add(getCard);





        boolean validarNumTarjLong = validarLongitudTarjeta(getCard);
        System.out.println("la longitud de la tarjeta es 16?:"+validarNumTarjLong);

        boolean tarjVencid = tarjetaVencida(getCard);
        System.out.println("la tarjeta esta vencida? :"+ tarjVencid);

        String validarNumTarj = enmascararNumeroTarjeta(getCard);
        System.out.println("remplazo el nuevo num de tarjeta: " + validarNumTarj);

        List<CardDto> listaTipoTarj = filtrarPorTipo(cardDtoList,"VISA");
        System.out.println("La tarjeta solicitada es: " + listaTipoTarj);

        generarReporteTarjetas(cardDtoList);

        generarReporteTarjetasVencidas(cardDtoList);

        contarTarjetasPorTipo(cardDtoList);

        generarReporteCvvOculto(cardDtoList);

        agruparTarjetasPorFecha(cardDtoList);

        filtrarTarjetasPorRango(cardDtoList, "1000000000000001","1000000000000003");



        /*boolean valid = true;
        String palabra = "aslkjdklas";
        int num = 123;

        valid = validar();
        palabra =crearPalabra();
        num = crearNum();

        int convertir = convertirPalabra("213","234");
        System.out.println("convertir: " + convertir);

        int suma = sumar(123123, 123214);
        System.out.println("suma = " + suma);

        boolean esPar = esPar(233);
        System.out.println("esPar = " + esPar);

        int factorial = calcularFactorial(3);
        System.out.println("factorial = " + factorial);

        int maximo = mayorDeTres(7,9,30);
        System.out.println("maximo = " + maximo);

        boolean palindromo = esPalindromo("1223421");
        System.out.println("palindromo = " + palindromo);

        int contarVocales = contarVocales("dinosaurio");
        System.out.println("contarVocales = " + contarVocales);

        verificarNumero(-0);

        contarPares();

        verificarDivisible(7);

        int sumaNumerosFor = sumarNumeros(100);
        System.out.println("sumaNumeros: "+sumaNumerosFor);

        int sumarImpares = sumarImpares(new int[]{234,234,123,21,4,23,1,1,23,345,35,21});
        System.out.println("impares:" + sumarImpares);


        int tablaMulti = tablaMultiplicar(2);
        System.out.println( tablaMulti);

        int calificacion = calificacionAprobatoria(60);
        System.out.println(calificacion);
        imprimirTriangulo(5);

        int sumarParesRango = sumarParesEnRango(1,10);
        System.out.println("sumaParesRango: " +sumarParesRango);

        int[] invertirArreglo = invertirArreglo(new int[]{12,2,3,124,12,123,12412,23});
        System.out.println("invertir arreglo: "+ Arrays.toString(invertirArreglo));

        boolean validarContra= validarContrasena("paquito123");


        int edadDescuento = calcularDescuento(59);*/

        return ResponseEntity.ok(lista);

    }

    private void showCardsReport(List<CardDto> cardDtoList) {
        System.out.println(cardDtoList);
    }



    public void filtrarTarjetasPorRango(List<CardDto> cards, String inicio, String fin){
        List<CardDto> list = new ArrayList<>();
        BigInteger inicioCadena = new BigInteger(inicio);
        BigInteger finCadena = new BigInteger(fin);

        for (CardDto card: cards) {
            BigInteger cardNumberInt = new BigInteger(card.getCardNumber());

            if (cardNumberInt.compareTo(inicioCadena) >= 0 && cardNumberInt.compareTo(finCadena) <= 0) {

                list.add(card);
            }

        }
        System.out.println("Tarjetas en el rango " + inicio + " - " + fin + ":");

        for(CardDto card: list){

            String enmascararNum = enmascararNumeroTarjeta(card);

            System.out.println("numero de tarjeta:" + enmascararNum + "  tipo: " + card.getCardType());
        }

    }


    public void agruparTarjetasPorFecha(List<CardDto> cards){
        List<CardDto> list = new ArrayList<>();
        List<CardDto> list1 = new ArrayList<>();
        for (CardDto card: cards){

            if (card.getExpireDate().equals("12/27")){
                list.add(card);
                System.out.println("Tarjetas con fecha 12/27"+list);
            } else {
                list1.add(card);

                System.out.println("Tarjetas con fecha 12/28"+list1);
            }
        }
    }


    public void generarReporteCvvOculto(List<CardDto> cards){
        for(CardDto cvv: cards){
            String enmascararNum = enmascararNumeroTarjeta(cvv);
            String ocultarCvv = enmascararNumeroCvv(cvv);
            System.out.println("numero de tarjeta: "+ enmascararNum+" Tipo: "+cvv.getCardType()+" CVV: "+ocultarCvv);
        }
    }



    public void contarTarjetasPorTipo(List<CardDto> cards){
        int visa = 0;
        int masterCard = 0;
        for(CardDto contarTarj: cards){
            if( contarTarj.getCardType().equals("VISA") ){
                visa++;
            } else if (contarTarj.getCardType().equals("MASTERCARD")) {
                masterCard++;
            }
        }
        System.out.println("VISA: "+visa+" tarjetas");
        System.out.println("MASTERCARD: "+masterCard+" tarjetas");

    }

    public void generarReporteTarjetasVencidas(List<CardDto> cards){
        for(CardDto tarjVenc: cards){
            String enmascararNum = enmascararNumeroTarjeta(tarjVenc);
            boolean validTarjVencid = tarjetaVencida(tarjVenc);
            if( validTarjVencid) {
                System.out.println("numero de tarjeta:" + enmascararNum + "  fecha expiracion: " + tarjVenc.getExpireDate() + validTarjVencid);
            }else{
                System.out.println("la tarjeta no esta vencida");
            }
        }
    }

    public void generarReporteTarjetas(List<CardDto> cards){

        for (CardDto cardNumber: cards){
            String enmascararNum = enmascararNumeroTarjeta(cardNumber);
            System.out.println(enmascararNum);
        }
    }

    public List<CardDto> filtrarPorTipo(List<CardDto> cards, String tipo){
        List<CardDto> list = new ArrayList<>();
        for (CardDto card: cards){
            if (card.getCardType().equalsIgnoreCase(tipo)){
                list.add(card);
            }
        }
        return list;
    }

    public String enmascararNumeroCvv(CardDto card){

        String nuevoCvv = card.getCvv().replace(card.getCvv(), "***");

        return nuevoCvv;
    }



    public String enmascararNumeroTarjeta(CardDto card){
        String ultimosDigitos = card.getCardNumber().substring(12, 16);
        String nuevoNumTarj = "**** **** **** " + ultimosDigitos;
        return nuevoNumTarj;

    }

    public boolean tarjetaVencida(CardDto card){
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("MM/yy");
        YearMonth fechaActual = YearMonth.now();





        YearMonth fecha = YearMonth.parse(card.getExpireDate(),formato);

        if (fecha.isBefore(fechaActual)) {
            return true;
        }
        return false;
    }

    public boolean validarLongitudTarjeta(CardDto card){
        if(card.getCardNumber().length()!=16){
            return false;
        }
        return true;
    }




    /*public int calcularDescuento(Integer edad) {
        if(edad < 60){
            System.out.println("tienes un descuento de 10% por ser menor de 60 años");
        }else {
            System.out.println("tienes un descuento de 30% por ser mayor de 60 años");
        }
        return edad;
    }

    public boolean validarContrasena(String contrasena) {
        String contrasenaCorrecta = "paquito123";
       if(contrasena.equals(contrasenaCorrecta)){
           System.out.println("contraseña correcta,bienvenido paquito123");
           return true;
       }else {
           System.out.println("contraseña incorrecta");
           return false;
       }

    }


    public int[] invertirArreglo(int[] arreglo) {
        int[] resultado = new int[arreglo.length];
        for (int i = 0; i<arreglo.length; i++){
            resultado [i] = arreglo[arreglo.length -1 -i];
        }
        return resultado;
    }


    public int sumarImpares(int [] arreglo){
        int suma = 0;
        for (int i=0; i<= arreglo.length; i++ ){
            if (i % 2 !=0){
                suma = i;
            }
        }
        return suma;

    }

    public int sumarParesEnRango(int inicio, int fin) {
        int suma = 0;
        for(int i = inicio; i<=fin; i++) {
            if (i % 2==0){
                suma += i;

            }

        }
        return suma;
    }

    public void imprimirTriangulo(int n) {
        for (int i=1; i<=n;i++){
            for(int j= 1; j<= n- i ;j++){

            }
            for(int k = 1; k <= i ; k++){
                System.out.print(k+" ");
            }
            System.out.println();
        }
    }






    private int calificacionAprobatoria (int calificacion){
        if(calificacion >= 60){
            System.out.println("calificacion aprobatoria");
        }else{
            System.out.println("calificacion no aprobatoria");
        }
        return calificacion;
    }

    private int tablaMultiplicar (int numero){
        int resultado = 0;
        for (int i=1;i<=10;i++){
            resultado = numero * i;

            System.out.println("multiplica "+ numero +"x " + i +" = "+ resultado);
        }
        return resultado;
    }

    private int convertirPalabra (String palabra,String palabra2){
        int numero = Integer.parseInt(palabra) + Integer.parseInt(palabra2);
        return numero;
    }

    private int crearNum (){
        return 123;
    }

    private String crearPalabra (){
        return "asdasdasd";
    }

    private boolean validar (){
        return  true;

    }


    public int sumarNumeros (int numero){
        int suma = 0;
        for(int i = 0; i<=numero; i++ ){
            suma = ((1+numero)*i/2);
        }
        return suma;
    }

    public int sumar(int a, int b){
        int resultado = a + b;
        return resultado;
    }

    public boolean esPar (int numero){
        if (numero % 2==0){
            return true;
        }else{
            return false;
        }
    }

    public int calcularFactorial (int numero){
        int resultado = 1;
        for (int i = 2; i <= numero; i++){
            System.out.println("i = " + numero);
            resultado = resultado * i;
        }
        return resultado;
    }

    public int mayorDeTres (int a, int b, int c){
        int maximo = 0;
        if (a == c && b == c){
        }else {
            if (a>b && a>c){
                maximo = a;
            }else {
                if (b>a && b>c){
                    maximo = b;
                }else {
                    if (c>a && c>b){
                        maximo = c;
                    }
                }
            }
        }
        return maximo;
    }

    public boolean esPalindromo (String palabra){
        for (int i= 0, j = palabra.length() - 1; i < j; i++, j--){
            if(palabra.charAt(i) != palabra.charAt(j)){
                return false;
            }
        }
        return true;
    }




    public int contarVocales (String texto){

        int contador = 0;

        for (int i = 0; i < texto.length(); i = i+1){
            char vocal = texto.charAt(i);
            if (vocal == 'a'||vocal =='e'||vocal =='i'||vocal =='o'||vocal =='u'){
                contador = contador + 1;
            }

        }
        return contador;

    }

    public void verificarNumero(int numero){


        if (numero >0){
            System.out.println("el numero es positivo");
        }else if(numero <0){
            System.out.println("el numero es negativo");
        }else {
            System.out.println("el numero es 0");
        }

    }

    public void contarPares(){
        for (int i = 1; i <= 100; i++){
            if ( i % 2==0){
                System.out.println(i);
            }
        }
    }

    public void verificarDivisible(int numero){
        if(numero % 3==0 && numero % 5==0){
            System.out.println("el numero es divisible por 3 y 5");
        }else{
            System.out.println("el numero no es divisible por ambos");
        }
    }*/




}

