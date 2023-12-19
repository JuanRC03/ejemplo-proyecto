package com.backend.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import org.springframework.stereotype.Component;

@Component
public class Fechas {

    public Fechas() {
    }
    
     public Date getFechaActual(){
        Date fechaActual = new Date();
        return fechaActual;
    }
    
    public static Date convertirAFecha(String stringFecha) {
        String[] formatos = {"yyyy-MM-dd", "dd/MM/yyyy", "dd-MM-yyyy", "yyyy/MM/dd"};
        for (String formato : formatos) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat(formato);
                System.out.println("decha: "+sdf);
                Date fechaAux=sdf.parse(stringFecha);
                return sdf.parse(stringFecha);
            } catch (ParseException e) {
                return null;
            }
        }
        return null; 
    }
    
    public boolean compararFechasAumentadas(String fecha1Str, String fecha2Str) {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date fecha1 = formato.parse(fecha1Str);
            Date fecha2 = formato.parse(fecha2Str);

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(fecha1);

            calendar.add(Calendar.DAY_OF_MONTH, 1);

            Date fecha1Aumentada = calendar.getTime();

            return fecha1Aumentada.equals(fecha2);
        } catch (Exception e) {
            e.printStackTrace();
            return false; 
        }
    }

    
    public boolean validarDosFechasSuscripcion(Date fecha1, Date fecha2) {
        return fecha1.compareTo(fecha2) <= 0;
    }
    
    public boolean validarFecha(int year, int month, int day) {
        try {
            LocalDate date = LocalDate.of(year, month, day);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
    public Date Fecha(String dateString) {
        dateString=cambiarGuionesABarras(dateString);
        System.out.println("fecha: "+dateString);
        try {
            LocalDate date = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            int day = date.getDayOfMonth();
            int month = date.getMonthValue();
            int year = date.getYear();
            if(validarFecha(year,month,day)){
                 return fechaFormato(dateString);
            }else{
                return null;
            }
        } catch (Exception ex) {
            System.out.println("llega al error");
            LocalDate date = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            int day = date.getDayOfMonth();
            int month = date.getMonthValue();
            int year = date.getYear();
            String fechaFinal=year+"-"+month+"-"+day;
            if(validarFecha(year,month,day)){
                 return fechaFormato(fechaFinal);
            }else{
                return null;
            }
        }
    }
    
    public String cambiarGuionesABarras(String fechaConGuiones) {
        return fechaConGuiones.replace("/", "-");
    }
    
    public Date fechaFormato(String stringFecha){
        try{
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            return formato.parse(stringFecha);
        }catch(Exception e){
            try {
                SimpleDateFormat formatoDos = new SimpleDateFormat("dd-MM-yyyy");
                return formatoDos.parse(stringFecha);
            } catch (ParseException ex) {
                return null;
            }
        }
    }
    
    public Date fechaDumi(){
        try{
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            return formato.parse("1900-1-1");
        }catch(Exception e){
            return null;
        }
    }
    public boolean validarCedula(String id) {
        if (id == null || id.length() != 10) {
            return false;
        }

        int sum = 0;
        for (int i = 0; i < 9; i++) {
            int digit = id.charAt(i) - '0';
            if ((i+1) % 2 == 0) {
                sum += digit;
            } else {
                sum += digit * 2;
                if (digit >= 5) {
                    sum -= 9;
                }
            }
        }
        int lastDigit = id.charAt(9) - '0';
        return lastDigit == (10 - sum % 10) % 10;
    }
    
   
    
    public boolean conpararFechaMasUnDia(Date fechaCreacion , Date fechaActual){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaCreacion);
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date fechaCreacionMasUnDia = calendar.getTime();
        
        if (fechaCreacionMasUnDia.before(fechaActual)) {
            return true;
        } else{
            return false;
        }
    }
    
    public Date agregarSieteDias(Date fechaInicial){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaInicial);
        calendar.add(Calendar.DAY_OF_YEAR, 7);
        Date fechaNueva = calendar.getTime();
        return fechaNueva;
    }
    
    public Date agregarTreintaDias(Date fechaInicial){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaInicial);
        calendar.add(Calendar.DAY_OF_YEAR, 30);
        Date fechaNueva = calendar.getTime();
        return fechaNueva;
    }
}
