package com.backend.banorte.service;

import com.backend.banorte.adapters.rest.DebitCardRestAdapter;
import com.backend.banorte.domain.dto.AccountDto;
import com.backend.banorte.domain.dto.CardDto;
import com.backend.banorte.domain.request.GetUserCardListRequest;
import com.backend.banorte.ports.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class AccountService implements AccountServicePort {



    @Autowired
    DebitCardRestAdapter debitCardRestAdapter;

    @Autowired
    AccountJpaPort accountJpaPort;

    @Override
    public ResponseEntity<List<AccountDto>> getAccountList(GetUserCardListRequest request,String auth){



        boolean comprobarUserPassword = debitCardRestAdapter.loginValido(auth);
        if(comprobarUserPassword){
            System.out.println("la cuenta es correcta");
        }else {
            System.out.println("la cuenta no es correcta");
        }


        List<AccountDto> lista = accountJpaPort.getCardList(request.getAccountNumber());


        CardDto getCard = new CardDto();
        getCard.setAccountNumber("100000000001");
        getCard.setCardNumber("1234567890123456");
        getCard.setCardType("MASTERCARD");
        getCard.setExpireDate("12/26");
        getCard.setCvv("344");

        List<CardDto> cardDtoList =new ArrayList<>();
        CardDto card = new CardDto();
        card.setAccountNumber("100000000001");
        card.setCardNumber("1000000000000001");
        card.setCardType("MASTERCARD");
        card.setExpireDate("12/24");
        card.setCvv("344");
        CardDto card1 = new CardDto();
        card1.setAccountNumber("100000000002");
        card1.setCardNumber("1000000000000002");
        card1.setCardType("VISA");
        card1.setExpireDate("12/23");
        card1.setCvv("344");
        CardDto card2 = new CardDto();
        card2.setAccountNumber("100000000003");
        card2.setCardNumber("1000000000000003");
        card2.setCardType("VISA");
        card2.setExpireDate("12/21");
        card2.setCvv("344");

        cardDtoList.add(card);
        cardDtoList.add(card1);
        cardDtoList.add(card2);
        cardDtoList.add(getCard);


        List<AccountDto> accountDtoList = new ArrayList<>();
        AccountDto account = new AccountDto();
        account.setFullName("Carlangas Pablo Rosa");
        account.setUsername("JUANP");
        account.setAccountNumber("100000000001");
        account.setPin("12345");
        account.setEmail("juanPR@gmail.com");
        account.setPhoneNumber("5500000001");
        AccountDto account1 = new AccountDto();
        account1.setFullName("Pedro Garza Luna");
        account1.setUsername("PEDROG");
        account1.setAccountNumber("100000000002");
        account1.setPin("5678");
        account1.setEmail("pedroGL@hotmail.com");
        account1.setPhoneNumber("5500000002");
        AccountDto account2 = new AccountDto();
        account2.setFullName("Carlos Medina Hernandez");
        account2.setUsername("CARLOSM");
        account2.setAccountNumber("100000000003");
        account2.setPin("9012");
        account2.setEmail("calorsMH@gmail.com");
        account2.setPhoneNumber("5500000003");

        accountDtoList.add(account);
        accountDtoList.add(account1);
        accountDtoList.add(account2);


        boolean validEmail = validarEmail(account);
        System.out.println(validEmail);

        String generarUsernam = generarUsername(account);
        System.out.println(generarUsernam);

        String enmasNumCuent = enmascararNumeroCuenta(account);
        System.out.println(enmasNumCuent);

        boolean validPin = validarPin(account);
        System.out.println(validPin);

        List<AccountDto> listTipEmail = filtrarPorDominio(accountDtoList,"@gmail.com");

        generarListadoNombresYCorreos(accountDtoList);


        List<AccountDto> buscarNombre = buscarPorNombre(accountDtoList,"Car");
        System.out.println(buscarNombre);

        List<AccountDto> numValidList = filtrarNumerosValidos(accountDtoList);
        System.out.println(numValidList);

        boolean verficiartarjeta = verificarTarjetaPorCuenta(account,card);

        List<CardDto> obtCardPorCuenta = obtenerTarjetasPorCuenta(account1, cardDtoList);

        generarReporteCuentasYTArjetas(accountDtoList,cardDtoList);

        boolean verificFechaExp = validarFechasDeExpiracion(account1, cardDtoList);


        CardDto resultado = obtenerTarjetaMasCercanaAExpirar(account, cardDtoList );



        List<AccountDto> buscarPorTipoTarj = buscarCuentasPorTipoDeTarjeta(accountDtoList, cardDtoList,"VISA");

        return ResponseEntity.ok(lista);
    }


    public List<AccountDto> buscarCuentasPorTipoDeTarjeta(List<AccountDto> accounts, List<CardDto> cards, String tipoTarjeta){
        List<AccountDto> list = new ArrayList<>();

        for (AccountDto account: accounts){
            for (CardDto card: cards){
;
                if (card.getAccountNumber().equals(account.getAccountNumber())&&card.getCardType().equals(tipoTarjeta)){
                    list.add(account);
                    String cuenta = "Cuenta: "+ account.getFullName()+ " Tipo de tarjeta: "+ tipoTarjeta;
                    System.out.println(cuenta);
                }
            }
        }
        return list;
    }


    public CardDto obtenerTarjetaMasCercanaAExpirar(AccountDto account, List<CardDto> cards){
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("MM/yy");
        CardDto tarjetaProxima = cards.get(0);
        for (CardDto card: cards){
            if(card.getAccountNumber().equals(account.getAccountNumber())) {
                YearMonth fechaTarjeta = YearMonth.parse(card.getExpireDate(), formato);
                YearMonth fechaProximaExpiracion =YearMonth.parse(tarjetaProxima.getExpireDate(),formato);;
                if(fechaTarjeta.isBefore(fechaProximaExpiracion)){
                    tarjetaProxima = card;

                }
            }
        }
        String resultado = "Resultado esperado: Tarjeta: " + tarjetaProxima.getCardNumber();
        System.out.println( resultado);
        return tarjetaProxima;

    }
    // Cuenta: John Doe, Tarjetas: [111122223333444,6556655656564,577557577557574646]
    public void generarReporteCuentasYTArjetas(List<AccountDto> accounts, List<CardDto> cards){
        System.out.println("Salida esperada: ");
        for (AccountDto account: accounts){
            String cuenta = "Cuenta: "+ account.getFullName()+", Tarjetas: [";


           for (CardDto card: cards){
               if (card.getAccountNumber().equals(account.getAccountNumber())){
                   cuenta = cuenta + card.getCardNumber()+ "," ;
               }
           }

            cuenta= cuenta.substring(
                    0, cuenta.length()-1
            );
            cuenta = cuenta + "]";



            System.out.println(cuenta);
        }



    }

    public boolean validarFechasDeExpiracion(AccountDto account, List<CardDto> cards){

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("MM/yy");
        YearMonth fechaActual = YearMonth.now();
        System.out.println("Fechas de Expiracion : ");
        for (CardDto card: cards){
            if(card.getAccountNumber().equals(account.getAccountNumber())) {
                YearMonth fecha = YearMonth.parse(card.getExpireDate(), formato);
                if (fecha.isBefore(fechaActual)) {
                    System.out.println("las tarjetas tienes fecha de expiracion invalida");
                    return false;
                }
            }

        }
        System.out.println("las tarjetas tienes fecha de expiracion valida");


        return true;
    }



    public List<CardDto> obtenerTarjetasPorCuenta(AccountDto account, List<CardDto> cards){
        List<CardDto> list = new ArrayList<>();

        System.out.println();
        for (CardDto card: cards){
            if (card.getAccountNumber().equals(account.getAccountNumber())){
                list.add(card);
                String cuenta = "Cuenta: "+ account.getFullName()+ " Tipo de tarjeta: " + card.getCardNumber();
                System.out.println(cuenta);
            }
        }

        return list;
    }



    public boolean verificarTarjetaPorCuenta(AccountDto account, CardDto card){
        if(account.getAccountNumber().equals( card.getAccountNumber())){
            System.out.println("ambas cuentas coinciden");
            return true;
        }
        return false;
    }

    public List<AccountDto> filtrarNumerosValidos(List<AccountDto> accounts){
        List<AccountDto> list = new ArrayList<>();
        for (AccountDto evalNumTel: accounts){
            if(evalNumTel.getPhoneNumber().length()==10){
                list.add(evalNumTel);
            }

        }
        return list;
    }


    public List<AccountDto> buscarPorNombre(List<AccountDto> accounts, String nombre){
        List<AccountDto> list = new ArrayList<>();

        for (AccountDto evalNombre: accounts){
            if (evalNombre.getFullName().contains(nombre)){
                list.add(evalNombre);
            }
        }
        return list;
    }

    public void generarListadoNombresYCorreos(List<AccountDto> accounts){
        for (AccountDto accountlit: accounts){
            String nombreCompl = accountlit.getFullName();
            String correo = accountlit.getEmail();
            System.out.println("nombre: " +nombreCompl + "Email: "+ correo);
        }
    }


    public List<AccountDto> filtrarPorDominio(List<AccountDto> accounts, String dominio){
        List<AccountDto> list = new ArrayList<>();
        for (AccountDto email: accounts){
            if (email.getEmail().endsWith(dominio)){
                boolean evaluarCorreo = validarEmail(email);
                if (evaluarCorreo){
                    list.add(email);
                }
            }
        }
        return list;
    }

    public boolean validarPin(AccountDto account){
        if(account.getPin().length()!=4){
            System.out.println("tu pin tiene mas de 4 digitos");
            return false;
        }
        return true;

    }

    public String enmascararNumeroCuenta(AccountDto card){
        String ultimosDigitos = card.getAccountNumber().substring(8, 12);
        String nuevoNumCuenta = "**** **** " + ultimosDigitos;
        return nuevoNumCuenta;

    }

    public String generarUsername(AccountDto account) {

        String nommbreCompleto = account.getFullName();
        String[] partes = nommbreCompleto.split(" ");
        char parte1 = partes[0].charAt(0);
        String parte2 = partes[1].substring(0,3);

        String usernameConstruido = parte1+parte2;
        System.out.println(parte1+parte2);
        return usernameConstruido;
    }


    public boolean validarEmail(AccountDto account){
        String emailREGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailREGEX);
        if(pattern.matcher(account.getEmail()).matches()){
            System.out.println("el correo electronico evaluado es: "+account.getEmail());
            return true;
        }
        return false;

    }

}
