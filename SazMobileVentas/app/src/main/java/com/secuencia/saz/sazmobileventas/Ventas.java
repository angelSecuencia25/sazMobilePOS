package com.secuencia.saz.sazmobileventas;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;

import android.os.Build;
import android.os.Bundle;

import android.support.v4.app.Fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.secuencia.saz.sazmobileventas.Modelo.DatosLupita;

import com.secuencia.saz.sazmobileventas.Modelo.ModeloEmpresa;
import com.secuencia.saz.sazmobileventas.Modelo.ModeloNumeroOrden;

import com.secuencia.saz.sazmobileventas.Modelo.ModeloUsuario;
import com.secuencia.saz.sazmobileventas.Modelo.Pago;
import com.secuencia.saz.sazmobileventas.Modelo.Similar;
import com.secuencia.saz.sazmobileventas.conexion.ConexionBDCliente;
import com.secuencia.saz.sazmobileventas.conexion.ConexionSQLiteHelper;
import com.secuencia.saz.sazmobileventas.conexion.ConexionSqlServer;
import com.secuencia.saz.sazmobileventas.utilidades.AdaptadorPago;
import com.secuencia.saz.sazmobileventas.utilidades.ModeloTienda;
import com.secuencia.saz.sazmobileventas.utilidades.TemplatePDF;
import com.secuencia.saz.sazmobileventas.utilidades.Utilidades;



import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.ResultSet;

import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import java.util.Locale;





public class Ventas extends Fragment {

    int cantidadRegistros;

    static int scu=0;
    static String colorBar, acabadoBar, marcaBar, corridaBar;


    String empress;
    public static String NombreTienda;
    String idImagen;
    static Context context;
    String ubica;

    Double resultado=0.0;
    boolean restar=false;

    Button btnDetalle;
    boolean pagado=false;

    String metodoPago;
    double efectivoPagado=0.0;

    String where=" where ";

    static Double puntoBar;
    public static ModeloEmpresa me=new ModeloEmpresa();
    public static ConexionBDCliente bdc=new ConexionBDCliente();
    ConexionSqlServer conex=new ConexionSqlServer();
    int cantidad=0;
    public static int up=0;
    public static String VarEstilo;
    static String BarCodeFIN="";
    static  String estiloBar="";

    String variableS,descuento;


    int iva=0;

    public static ArrayList lista=new ArrayList();
    ArrayList listaAcabado=new ArrayList();
    ArrayList listaBancos=new ArrayList();
    ArrayList listaSocio=new ArrayList();
    static String CodigoBar="";
    ArrayList listaMarca=new ArrayList();
    ArrayList listaCorrida=new ArrayList();
    static ArrayList puntos=new ArrayList();
    String numeroUsuario;
    TextView idPedido;
    boolean xcaner=false;
    ModeloTienda mt=new ModeloTienda();

    String barcode;
    Button btnMas, btnMenos, btnAgregar,btnResumen;

    String dispositivo;

    static int colorCantidad=0, acabadoCantidad=0, marcaCantidad=0, corridaCantidad=0,puntosCantidad=0;

    TextView precioTXT;

    Button btnBardoce;
    double tot;

    static String sku;
    String intentResummen;
    public static double pre = 0.0;
    double precio=0.0;

    public static int   existencias;

    public static Spinner spColor,spAcabado, spMarca, spCorrida, punto, spSocio;

    public static TextView existenciasTXT, cantidadTXT, unidadesTXT, importeTXT,descuentoTXT,totalTXT;

    public static EditText sp2;
    static TextView clienteTXT;

    double r;
    static String in, finn, inc;
    String color, acabado, marcas;
    public static String listado;

    static String idFecha;

    Double cambio = 0.0;
    ModeloUsuario mu=new ModeloUsuario();

    String NombreUsuario;
    View root;

    Button btnSimilares, btnPagar;
    String calle,tel,ciudad,estado,cp,idColonia;


    String d1,d2,d3,d4,d5,d6,d7,d8,d9,d0,vd,cf,vi,e1,e2,e3,e4,e5,e6,e7,e8,e9,e0;

    static  String idMarca=null;
    Button btnOtras;
    EditText efectivoTxt;
    static DatosLupita dl=new DatosLupita();

    Spinner spBanco;

    ArrayList<Pago> listaPago;
    int idContenedor=0;

    RecyclerView recyclerView;

    double descuentoFinal;
    double auxiliarDes;

    int  buscador=0;

    private String[] header={"Cantidad","Descripción","Desc %","Importe"};

    private String[] headerPagos={"Formas de pago","Monto"};
    String[] headerTotal={"    ","   ","    "};

    double subtotalTicket;


    private String sucursal="Sucursal: "+ mt.getNumeroTienda();


    private TemplatePDF pdf;

    TextView monto,forma;

    String rfc, razon,fechaTicket;

    TextView txtSaldoRestante;





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        root=inflater.inflate(R.layout.fragment_ventas, container, false);
        context=root.getContext();



        listado=mt.getNombreTienda();


        ToolBarNombre();

        getUser();

        sp2=(EditText)root.findViewById(R.id.sp2);
        btnDetalle=(Button)root.findViewById(R.id.btnDetalle);


        spColor =(Spinner)root. findViewById(R.id.sp);
        spAcabado=(Spinner)root.findViewById(R.id.sp3);
        spMarca=(Spinner)root.findViewById(R.id.sp4);
        spCorrida=(Spinner)root.findViewById(R.id.sp5);
        punto=(Spinner)root.findViewById(R.id.punto);
        precioTXT=(TextView)root.findViewById(R.id.precioTXT);
        cantidadTXT=(TextView)root.findViewById(R.id.cantidadTXT);
        clienteTXT=(EditText)root.findViewById(R.id.clienteTXT);
        btnMas=(Button)root.findViewById(R.id.btnMas);
        btnMenos=(Button)root.findViewById(R.id.btnMenos);
        btnAgregar=(Button)root.findViewById(R.id.btnAgregar);
        importeTXT=(TextView)root.findViewById(R.id.importeTXT);
        unidadesTXT=(TextView)root.findViewById(R.id.unidadesTXT);
        descuentoTXT=(TextView)root.findViewById(R.id.descuentoTXT);
        totalTXT=(TextView)root.findViewById(R.id.totalTXT);
        btnBardoce=(Button)root.findViewById(R.id.btnBarCode);
        existenciasTXT=(TextView)root.findViewById(R.id.existenciasTXT);
        btnPagar=(Button)root.findViewById(R.id.btnPagar);
        spBanco=(Spinner)root.findViewById(R.id.spBanco);
        efectivoTxt=(EditText)root.findViewById(R.id.efectivoTXT);

        btnResumen=(Button)root.findViewById(R.id.btnResumen);
        monto=(TextView)root.findViewById(R.id.montoPagoLabel);
        forma=(TextView)root.findViewById(R.id.formaPagoLabel);
        spSocio=(Spinner)root.findViewById(R.id.spSocio);
        btnSimilares=(Button)root.findViewById(R.id.btnSimilar);
        txtSaldoRestante=(TextView)root.findViewById(R.id.txtSaldoRestante);
        btnOtras=(Button)root.findViewById(R.id.btnOtras);
        mismoDispositivo.start();

        unidadesTXT.setText("0");
        importeTXT.setText("0");

        monto.setVisibility(View.INVISIBLE);
        forma.setVisibility(View.INVISIBLE);
        modoBusqueda.start();

        if(Integer.parseInt(unidadesTXT.getText().toString())>0){
            efectivoTxt.setEnabled(true);
        }

        btnOtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(punto.getSelectedItem()=="") {
                    Toast.makeText(getActivity(),"Debes seleccionar estilo y punto ",Toast.LENGTH_LONG).show();
                }else{
                    try {


                        dl.setBarcode(barcode);
                        dl.setPunto(punto.getSelectedItem().toString());

                        Intent tienda = new Intent(getActivity(), Tiendas.class);
                        startActivity(tienda);
                    }catch (Exception ex){
                        Toast.makeText(getActivity(),"Debes Ingresar un  estilo ",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });


        btnSimilares.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(punto.getSelectedItem()=="") {
                    Toast.makeText(getActivity(),"Debes seleccionar estilo y punto ",Toast.LENGTH_LONG).show();
                }else{
                    try {

                        Principal.punto=punto.getSelectedItem().toString();
                        dl.setBarcode(barcode);
                        dl.setPunto(punto.getSelectedItem().toString());
                        Intent similar=new Intent(getActivity(),Similares.class);
                        startActivity(similar);
                    }catch (Exception ex){
                        Toast.makeText(getActivity(),"Debes Ingresar un  estilo ",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });




        getSocio();
        ArrayAdapter<String> adapterSocio = new ArrayAdapter<String>(getActivity(),R.layout.spinner_item, listaSocio);
        spSocio.setAdapter(adapterSocio);


        if (Principal.banco=true) {



            obtenerBanco();
            ArrayAdapter<String> adapterr = new ArrayAdapter<String>(getActivity(),R.layout.spinner_item, listaBancos);
            spBanco.setAdapter(adapterr);
            btnPagar.setEnabled(true);
            Principal.banco=false;
        }
        btnMas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {

                        if (existencias > 0) {
                            existencias--;
                            existenciasTXT.setText(String.valueOf(existencias));
                            cantidad++;
                            String res = String.valueOf(cantidad);
                            cantidadTXT.setText(res);

                        } else {
                            Toast.makeText(getActivity(), "No hay existencias disponible de este producto", Toast.LENGTH_LONG).show();


                        }


                }catch(Exception e){

                }





            }
        });

        btnResumen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent resumen = new Intent(getActivity(), Resumen.class);
                resumen.putExtra("resumen", intentResummen);
                startActivity(resumen);


            }
        });



        btnPagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                monto.setVisibility(View.VISIBLE);
                forma.setVisibility(View.VISIBLE);

                  try {
                          if(!efectivoTxt.getText().toString().isEmpty()) {
                          String ticket = generarTicket();




                          Double precio = Double.parseDouble(importeTXT.getText().toString());
                          Double pagos = Double.parseDouble(efectivoTxt.getText().toString());


                          if(restar==false) {
                              resultado = precio - pagos;
                          }else if(restar==true){
                              resultado=resultado-pagos;
                          }

                          if (resultado == 0) {
                              registroPagos();
                              if(metodoPago!=null){
                                  if (metodoPago.equals(spBanco.getSelectedItem().toString()))
                                  {
                                      recyclerView = (RecyclerView) root.findViewById(R.id.recy);
                                      ultimaVez();
                                      updatePago();
                                      recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                                      mostrarPago();
                                      AdaptadorPago adapter = new AdaptadorPago(listaPago);
                                      recyclerView.setAdapter(adapter);

                                  }else{
                                      recyclerView = (RecyclerView) root.findViewById(R.id.recy);
                                      ultimaVez();
                                     insertarPago();
                                      recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                                      mostrarPago();
                                      AdaptadorPago adapter = new AdaptadorPago(listaPago);
                                      recyclerView.setAdapter(adapter);
                                  }
                              }else{
                                  recyclerView = (RecyclerView) root.findViewById(R.id.recy);
                                  ultimaVez();
                                  insertarPago();
                                  recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                                  mostrarPago();
                                  AdaptadorPago adapter = new AdaptadorPago(listaPago);
                                  recyclerView.setAdapter(adapter);
                              }

                              validarPago(ticket);

                              listaPago = new ArrayList<>();
                              recyclerView = (RecyclerView) root.findViewById(R.id.recy);
                              ultimaVez();
                              recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                              mostrarPago();
                              AdaptadorPago adapter = new AdaptadorPago(listaPago);
                              recyclerView.setAdapter(adapter);
                              limpiarCajas();
                              listaCorrida.clear();
                              listaMarca.clear();
                              listaAcabado.clear();
                              lista.clear();

                              sp2.setText(null);
                              restar=false;
                              efectivoTxt.setText(null);


                              btnPagar.setEnabled(false);

                              unidadesTXT.setText("0");
                              importeTXT.setText("0");
                              existenciasTXT.setText(null);
                              existencias=0;
                              pagado=true;

                          } else if (resultado < 0) {
                              cambio = resultado * -1;
                              registroPagos();
                              if(metodoPago!=null){
                                  if (metodoPago.equals(spBanco.getSelectedItem().toString()))
                                  {
                                      recyclerView = (RecyclerView) root.findViewById(R.id.recy);
                                      ultimaVez();
                                      updatePago();
                                      recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                                      mostrarPago();
                                      AdaptadorPago adapter = new AdaptadorPago(listaPago);
                                      recyclerView.setAdapter(adapter);

                                  }else{
                                      recyclerView = (RecyclerView) root.findViewById(R.id.recy);
                                      ultimaVez();
                                      insertarPago();
                                      recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                                      mostrarPago();
                                      AdaptadorPago adapter = new AdaptadorPago(listaPago);
                                      recyclerView.setAdapter(adapter);
                                  }
                              }else{
                                  recyclerView = (RecyclerView) root.findViewById(R.id.recy);
                                  ultimaVez();
                                  insertarPago();
                                  recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                                  mostrarPago();
                                  AdaptadorPago adapter = new AdaptadorPago(listaPago);
                                  recyclerView.setAdapter(adapter);
                              }
                              validarPago(ticket);

                              listaPago = new ArrayList<>();
                              recyclerView = (RecyclerView) root.findViewById(R.id.recy);
                              ultimaVez();
                              recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                              mostrarPago();
                              AdaptadorPago adapter = new AdaptadorPago(listaPago);
                              recyclerView.setAdapter(adapter);
                              limpiarCajas();
                              listaCorrida.clear();
                              listaMarca.clear();
                              listaAcabado.clear();
                              lista.clear();

                              sp2.setText(null);
                              restar=false;
                              efectivoTxt.setText(null);
                              btnPagar.setEnabled(false);

                              existencias=0;
                              pagado=true;
                              Toast.makeText(getActivity(),"Compra finalizada, Presiona el boton ticket para generarlo",Toast.LENGTH_LONG).show();
                          } else if (resultado > 0) {
                              registroPagos();
                              if(metodoPago!=null){
                                  if (metodoPago.equals(spBanco.getSelectedItem().toString()))
                                  {
                                      recyclerView = (RecyclerView) root.findViewById(R.id.recy);
                                      ultimaVez();
                                      updatePago();
                                      recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                                      mostrarPago();
                                      AdaptadorPago adapter = new AdaptadorPago(listaPago);
                                      recyclerView.setAdapter(adapter);

                                  }else{
                                      recyclerView = (RecyclerView) root.findViewById(R.id.recy);
                                      ultimaVez();
                                      insertarPago();
                                      recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                                      mostrarPago();
                                      AdaptadorPago adapter = new AdaptadorPago(listaPago);
                                      recyclerView.setAdapter(adapter);
                                  }
                              }else{
                                  recyclerView = (RecyclerView) root.findViewById(R.id.recy);
                                  ultimaVez();
                                  insertarPago();
                                  recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                                  mostrarPago();
                                  AdaptadorPago adapter = new AdaptadorPago(listaPago);
                                  recyclerView.setAdapter(adapter);
                              }
                              listaPago = new ArrayList<>();
                              recyclerView = (RecyclerView) root.findViewById(R.id.recy);
                              ultimaVez();
                              recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                              mostrarPago();
                              AdaptadorPago adapter = new AdaptadorPago(listaPago);
                              recyclerView.setAdapter(adapter);
                              efectivoTxt.setText(null);
                              restar=true;
                              pagado=false;
                              txtSaldoRestante.setText("Monto pendiente: "+resultado );


                          }


                      }else{
                          Toast.makeText(getActivity(),"Debes agregar un monto a pagar ",Toast.LENGTH_LONG).show();
                      }


                      if(pagado==true){

                              iva();

                          obtenerDatosTienda();
                          razonSocial();
                          pdf= new TemplatePDF(getActivity());
                          pdf.openDocument();
                          pdf.addImgName();
                          pdf.addMetaData("Productos","ventas","  PRUEBA");
                          pdf.addTitles("SazMobile Pos","Nota de venta", fechaTicket);
                          pdf.addParagraph(razon);
                          pdf.addParagraph("RFC: "+ rfc);
                          pdf.addParagraph(sucursal);
                          pdf.addParagraph("Lugar de expedición: "+obtenerColonia());
                          pdf.addParagraph(calle);
                          pdf.addParagraph("teléfono: "+tel);
                          pdf.addParagraph(estado);
                          pdf.addParagraph("Código postal: "+cp);
                          pdf.addParagraph("Vendedor: "+mu.getNombre());
                          if(spSocio.getSelectedItem().toString()!=null){
                              pdf.addParagraph("Vendido a"+spSocio.getSelectedItem().toString());
                          }else{
                              pdf.addParagraph("(-1) Ventas mostrador");
                          }

                          pdf.addParagraph("No. Ticket: "+generarTicket());
                          pdf.tablaProductos(header,getProductos());
                          pdf.tablaTotal(headerTotal,getSubTotal());
                          if(iva==1) {
                              pdf.tablaTotal(headerTotal, getIva());
                          }
                          pdf.tablaTotal(headerTotal,getTotal());
                          pdf.tablaPagos(headerPagos,getPagos());
                          pdf.addParagraph("* Gracias por su compra *");

                          pdf.closeDocument();


                          Principal.menu=true;
                          btnPagar.setEnabled(false);



                          deleteContenedor();
                          delete();


                          limpiarCajas();
                          sp2.setText(null);
                          lista.clear();
                          spColor.setAdapter(null);

                          listaAcabado.clear();
                          spAcabado.setAdapter(null);

                          listaMarca.clear();
                          spMarca.setAdapter(null);

                          listaCorrida.clear();
                          spCorrida.setAdapter(null);

                          limpiarCajas();

                          puntos.clear();
                          punto.setAdapter(null);

                          cantidadTXT.setText(null);
                          precioTXT.setText(null);

                          clienteTXT.setText(null);
                          unidadesTXT.setText("0");
                          importeTXT.setText("0");
                          precioTXT.setText(null);
                          descuentoTXT.setText(null);
                          totalTXT.setText(null);

                          listaSocio.clear();
                          listaSocio.add(null);
                          spSocio.setAdapter(null);

                          listaBancos.add(null);
                          listaBancos.add("");
                          listaBancos.clear();

                          spBanco.setAdapter(null);

                          up = 0;
                          pre = 0.0;
                          intentResummen = null;
                          tot = 0;
                          idFecha = null;
                          monto.setVisibility(View.INVISIBLE);
                          forma.setVisibility(View.INVISIBLE);

                          pdf.viewPDF();
                          dropDatos();
                      }else{
                          Toast.makeText(getActivity(),"No se puede generar el ticket hasta que la venta no se complete.", Toast.LENGTH_LONG).show();
                      }

                  }catch(Exception e){
                      e.getMessage();
                      Toast.makeText(getActivity()," No es posible realizar el pago. ",Toast.LENGTH_LONG).show();
                  }





            }
        });

        btnMenos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {


                    if (cantidad > 0) {

                        existencias++;
                        existenciasTXT.setText(String.valueOf(existencias));
                        cantidad--;
                        String res = String.valueOf(cantidad);
                        cantidadTXT.setText(res);
                    }
                }catch(Exception e){

                }
            }
        });


        if(Principal.similarPass==true){
            cargarDatosBarcodeSimilar(dl.getBarcode());
            Principal.similarPass=false;
        }

        formatoFecha.start();
        retomarPedidos();
        if(Integer.parseInt(unidadesTXT.getText().toString())>0){
            efectivoTxt.setEnabled(true);
        }else{
            efectivoTxt.setEnabled(false);
        }
        dropDatos();
        ConsultarNuevosRegistros();



        btnBardoce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xcaner=true;
                startActivity(new Intent(getActivity(),Scann.class));


            }
        });
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



try {

    int cantiTxt = Integer.parseInt(cantidadTXT.getText().toString());


    if (cantiTxt > 0) {
        efectivoTxt.setEnabled(true);


        generarImporte();
        generarCantidad();
        obtenerRegistrosSQLite();
        Finalizare();

        cantidadTXT.setText("0");
        descontarExistencias();


    } else {
        Toast.makeText(getActivity(), "No hay Producto", Toast.LENGTH_SHORT).show();
    }

}catch (Exception e){
    Toast.makeText(getActivity(), "No hay Producto", Toast.LENGTH_SHORT).show();
}

            }
        });



        btnDetalle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                   Toast toast =new  Toast(getActivity());
                    pl.droidsonroids.gif.GifImageView view=new  pl.droidsonroids.gif.GifImageView(getActivity());
                    view.setImageResource(R.drawable.loading);
                    toast.setView(view);
                    toast.show();
                    Intent detalle = new Intent(getActivity(), Detalle.class);

                    detalle.putExtra("valores", sp2.getText() + "-" + Principal.idColor + "-" + Principal.idAcabado + "-" + idMarca + "-" + empress);

                    startActivity(detalle);

                }catch (Exception e){

                }
            }
        });






        punto.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(punto.getSelectedItem()=="") {

                }else{
                    existenciasEnMiTienda();
                    traerPrecio();
                    obtenerBanco();
                    ArrayAdapter<String> adapterr = new ArrayAdapter<String>(getActivity(),R.layout.spinner_item, listaBancos);
                    spBanco.setAdapter(adapterr);
                    btnPagar.setEnabled(true);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        sp2.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                lista.clear();
                spColor.setAdapter(null);

                listaAcabado.clear();
                spAcabado.setAdapter(null);

                listaMarca.clear();
                spMarca.setAdapter(null);

                listaCorrida.clear();
                spCorrida.setAdapter(null);

                limpiarCajas();

                puntos.clear();
                punto.setAdapter(null);


                precioTXT.setText(null);

                btnPagar.setEnabled(false);
                existencias=0;

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });




        spColor.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    if (spColor.getWindowToken() != null) {
                        spColor.performClick();
                    }
                }
            }
        });




        sp2.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    try {

                    }catch (IllegalThreadStateException e){
                        e.getMessage();
                    }

                    buscador=0;

                    colorCantidad=0;
                    acabadoCantidad=0;
                    marcaCantidad=0;
                    colorCantidad=0;
                    puntosCantidad=0;



                    listaCorrida.clear();
                    listaMarca.clear();
                    listaAcabado.clear();
                    lista.clear();

                    limpiarCajas();



                    Toast.makeText(getActivity(), "Cargando...", Toast.LENGTH_LONG).show();

                    puntos.clear();


                    punto.setAdapter(null);

                    contarSp();
                    if(colorCantidad>0) {
                        if (Principal.scannPass == true && buscador == 0) {
                            llenarSp();
                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_item, lista);
                            spColor.setAdapter(adapter);
                            spColor.setSelection(obtenerPosicionItem(spColor, colorBar));
                        } else if (Principal.scannPass == false && colorCantidad == 1 && buscador == 0) {
                            llenarSp();
                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_item, lista);
                            spColor.setAdapter(adapter);
                            spColor.setSelection(1);


                        } else if (Principal.scannPass == false && colorCantidad > 1 && buscador == 0) {
                            llenarSp();
                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_item, lista);
                            spColor.setAdapter(adapter);

                        } else if (buscador == 1 && Principal.scannPass==false) {
                            buscarMarcas();

                            if (marcaCantidad == 1) {


                                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_item, listaMarca);
                                spMarca.setAdapter(adapter);
                                spMarca.setSelection(1);



                            } else if (marcaCantidad > 1) {

                                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_item, listaMarca);
                                spMarca.setAdapter(adapter);


                            }

                        } else if (Principal.scannPass == false && buscador == 0) {
                            llenarSp();
                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_item, lista);
                            spColor.setAdapter(adapter);

                        }else if(buscador == 1 && Principal.scannPass==true){
                            buscarMarcas();
                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_item, listaMarca);
                            spMarca.setAdapter(adapter);
                            spMarca.setSelection(obtenerPosicionItem(spMarca, marcaBar));

                        }
                    }else{
                        Toast.makeText(getActivity(),"Estilo no valido",Toast.LENGTH_LONG).show();
                    }


                    return true;
                }
                return false;
            }
        });




        spColor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast toast = Toast.makeText(getActivity(), "Cargando...", Toast.LENGTH_LONG);
                TextView x = (TextView) toast.getView().findViewById(android.R.id.message);
                x.setTextColor(Color.YELLOW); toast.show();

                listaAcabado.clear();
                listaMarca.clear();
                listaCorrida.clear();
                puntos.clear();
                spAcabado.setAdapter(null);
                spMarca.setAdapter(null);
                spCorrida.setAdapter(null);
                punto.setAdapter(null);
                precioTXT.setText(null);
                spAcabado.setAdapter(null);
                limpiarCajas();

                colorCantidad=0;
                acabadoCantidad=0;
                marcaCantidad=0;
                colorCantidad=0;
                puntosCantidad=0;

                listaAcabado.clear();


                contarSp3();

                if(Principal.scannPass==true){
                    llenarSp3();
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),R.layout.spinner_item, listaAcabado);
                    spAcabado.setAdapter(adapter);
                    spAcabado.setSelection(obtenerPosicionItem(spAcabado,acabadoBar));

                }else if(Principal.scannPass==false && acabadoCantidad==1){
                    llenarSp3();
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),R.layout.spinner_item, listaAcabado);
                    spAcabado.setAdapter(adapter);
                    spAcabado.setSelection(1);



                }else if(Principal.scannPass==false && acabadoCantidad>1){
                    llenarSp3();
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),R.layout.spinner_item, listaAcabado);
                    spAcabado.setAdapter(adapter);



                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }


        });



        spAcabado.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                listaMarca.clear();
                listaCorrida.clear();
                puntos.clear();


                limpiarCajas();

                spMarca.setAdapter(null);
                spCorrida.setAdapter(null);
                punto.setAdapter(null);

                colorCantidad=0;
                acabadoCantidad=0;
                marcaCantidad=0;
                colorCantidad=0;
                puntosCantidad=0;
                contarSp4();
                if(Principal.scannPass==true){
                    llenarSp4();
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),R.layout.spinner_item, listaMarca);
                    spMarca.setAdapter(adapter);
                    spMarca.setSelection(obtenerPosicionItem(spMarca,marcaBar));
                }else if(Principal.scannPass==false && marcaCantidad==1){
                    llenarSp4();
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),R.layout.spinner_item, listaMarca);
                    spMarca.setAdapter(adapter);
                    spMarca.setSelection(1);


                }else if(Principal.scannPass==false && marcaCantidad>1){
                    llenarSp4();
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),R.layout.spinner_item, listaMarca);
                    spMarca.setAdapter(adapter);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        spMarca.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                try {

                    String validador="";
                    validador=spMarca.getSelectedItem().toString();
                    if(validador.equals(" ")) {
                        listaCorrida.clear();
                        Principal.idCorrida="";
                        spCorrida.setAdapter(null);
                        puntos.clear();
                        punto.setAdapter(null);



                    }else{

                        spCorrida.setAdapter(null);
                        punto.setAdapter(null);
                        listaCorrida.clear();
                        puntos.clear();
                        limpiarCajas();

                        spCorrida.setAdapter(null);
                        listaCorrida.clear();


                        colorCantidad=0;
                        acabadoCantidad=0;
                        marcaCantidad=0;
                        colorCantidad=0;
                        puntosCantidad=0;

                        contarSp5();
                        if (Principal.scannPass == true && buscador == 0) {
                            llenarSp5();
                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_item, listaCorrida);
                            spCorrida.setAdapter(adapter);
                            spCorrida.setSelection(obtenerPosicionItem(spCorrida, corridaBar));
                        } else if (Principal.scannPass == false && corridaCantidad == 1 && buscador == 0) {
                            llenarSp5();
                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_item, listaCorrida);
                            spCorrida.setAdapter(adapter);
                            spCorrida.setSelection(1);

                        } else if (Principal.scannPass == false && corridaCantidad > 1 && buscador == 0) {
                            llenarSp5();
                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_item, listaCorrida);
                            spCorrida.setAdapter(adapter);

                        } else if (buscador == 1 && Principal.scannPass==false) {
                            llenarSp5();
                            obtenerCorrida();
                            if(corridaCantidad==1){
                                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_item, listaCorrida);
                                spCorrida.setAdapter(adapter);
                                spCorrida.setSelection(1);
                            }else if(corridaCantidad>1){
                                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_item, listaCorrida);
                                spCorrida.setAdapter(adapter);
                            }

                        } else if (Principal.scannPass == false && buscador == 0) {
                            llenarSp5();
                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_item, listaCorrida);
                            spCorrida.setAdapter(adapter);

                        }else if (buscador == 1 && Principal.scannPass==true) {
                            llenarSp5();
                            obtenerCorrida();

                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_item, listaCorrida);
                            spCorrida.setAdapter(adapter);
                            spCorrida.setSelection(obtenerPosicionItem(spCorrida, corridaBar));



                        }

                    }
                }catch(Exception e){

                }

            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        spCorrida.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                String validador="";
                validador=spCorrida.getSelectedItem().toString();
                if(validador.equals(" ")) {


                    puntos.clear();
                    punto.setAdapter(null);



                }else {
                    punto.setAdapter(null);
                    puntos.clear();
                    limpiarCajas();

                    puntos.clear();
                    punto.setAdapter(null);

                    colorCantidad = 0;
                    acabadoCantidad = 0;
                    marcaCantidad = 0;
                    colorCantidad = 0;
                    puntosCantidad = 0;


                    contarPuntos();
                    if (Principal.scannPass == true && buscador == 0) {
                        llenarTabla();
                        llenarPuntos();

                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_item_punto, puntos);
                        punto.setAdapter(adapter);
                        punto.setSelection(obtenerPosicionItem(punto, String.valueOf(puntoBar)));
                        Principal.scannPass = false;
                    } else if (Principal.scannPass == false && puntosCantidad == 1 && buscador == 0) {
                        llenarTabla();
                        llenarPuntos();

                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_item_punto, puntos);
                        punto.setAdapter(adapter);


                    } else if (Principal.scannPass == false && puntosCantidad > 1 && buscador == 0) {
                        llenarTabla();
                        llenarPuntos();

                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_item_punto, puntos);
                        punto.setAdapter(adapter);

                    } else if (buscador == 1 && Principal.scannPass == false) {
                        traerDatosProducto();
                        llenarPuntos();
                        if (puntosCantidad == 1) {
                            llenarTabla();

                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_item_punto, puntos);
                            punto.setAdapter(adapter);


                        } else if (puntosCantidad > 1) {
                            llenarTabla();
                            llenarPuntos();

                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_item_punto, puntos);
                            punto.setAdapter(adapter);

                        }


                    } else if (Principal.scannPass == false && buscador == 0) {
                        llenarTabla();
                        llenarPuntos();
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_item_punto, puntos);
                        punto.setAdapter(adapter);

                    } else if (buscador == 1 && Principal.scannPass == true) {

                        traerDatosProducto();
                        llenarPuntos();

                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_item_punto, puntos);
                        punto.setAdapter(adapter);
                        punto.setSelection(obtenerPosicionItem(punto, String.valueOf(puntoBar)));
                        Principal.scannPass = false;


                    }
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return root;
    }



    private void generarCantidad() {
        up += cantidad;
        retomarUnidades();
        cantidad = 0;





    }

    private void generarImporte() {

        double Total;

        auxiliarDes=Double.parseDouble(descuento)/100;
        descuentoFinal = Double.parseDouble(variableS)*auxiliarDes;
        BigDecimal bigDecimal=new BigDecimal(Double.parseDouble(variableS)- descuentoFinal).setScale(2, RoundingMode.UP);
        Total=bigDecimal.doubleValue();
        precioTXT.setText("$"+variableS);
        descuentoTXT.setText("%"+descuento);

        totalTXT.setText("$"+String.valueOf(Total));




        double resultado=Total * cantidad;
        retomarImporte(resultado);
        pre += resultado;
        precio+=resultado;






    }





    public void obtenerRegistrosSQLite(){


        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(context, "db tienda", null, 1);
        SQLiteDatabase db = conn.getReadableDatabase();


        String sql = "SELECT id FROM contenedor WHERE estilo='" +sp2.getText().toString()+ "' and talla="+punto.getSelectedItem();

        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {

            idContenedor=cursor.getInt(0);





        }

        if(idContenedor==0){
            insertarPedido.start();
            //retomarPedidos();
        }else{
            insertarRegistroIgual.start();
            //retomarPedidos();
        }


    }








    public void retomarPedidos(){
        int id=0;
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(context, "db tienda", null, 1);
        SQLiteDatabase db = conn.getReadableDatabase();


        Cursor cursor = db.rawQuery("SELECT id,SUM(cantidad),SUM(total),cliente FROM " + Utilidades.TABLA_CONTENEDOR , null);
        while (cursor.moveToNext()) {
            id=cursor.getInt(0);
            if(id!=0){
                unidadesTXT.setText(cursor.getString(1));
                importeTXT.setText(cursor.getString(2));
                clienteTXT.setText(cursor.getString(3));
            }



        }


    }


    public void retomarUnidades(){

        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(context, "db tienda", null, 1);
        SQLiteDatabase db = conn.getReadableDatabase();


        Cursor cursor = db.rawQuery("SELECT SUM(cantidad) FROM " + Utilidades.TABLA_CONTENEDOR , null);
        while (cursor.moveToNext()) {

                int contador=cursor.getInt(0);
                unidadesTXT.setText(String.valueOf(contador+cantidad));

        }


    }
    public void contarSp4() {

        listaMarca.clear();
        listaMarca.add(" ");
        Principal.idAcabado=getAcabado(spAcabado.getSelectedItem().toString());
        try {
            Statement st = bdc.conexionBD(me.getServer(),me.getBase(),me.getUsuario(),me.getPass()).createStatement();
            String sql="select DISTINCT  m.marca, numero from marcas m inner join articulo a on m.numero = a.marca where a.estilo = '"+sp2.getText()+"' and a.Color = "+Principal.idColor+" and a.acabado = "+Principal.idAcabado+"";
            ResultSet rs = st.executeQuery(sql);


            while (rs.next()) {

                marcaCantidad++;
                String add=(rs.getString(1));
                String s=(rs.getString(2));

            }
            st.close();


        } catch (Exception e) {
            Toast.makeText(getActivity(), "Error en sp4", Toast.LENGTH_SHORT).show();
        }

    }

    public void retomarImporte(double resultado){

        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(context, "db tienda", null, 1);
        SQLiteDatabase db = conn.getReadableDatabase();


        Cursor cursor = db.rawQuery("SELECT SUM(total) FROM " + Utilidades.TABLA_CONTENEDOR , null);
        while (cursor.moveToNext()) {

            double contador=cursor.getDouble(0);
            importeTXT.setText(String.valueOf(contador+resultado));


        }


    }
    public void deleteContenedor(){
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(context, "db tienda", null, 1);
        SQLiteDatabase db = conn.getReadableDatabase();
        db.execSQL("DELETE FROM  " + Utilidades.TABLA_CONTENEDOR);
        db.close();
    }









    public void Finalizare(){
        try {

            Statement st = bdc.conexionBD(me.getServer(), me.getBase(), me.getUsuario(), me.getPass()).createStatement();
            String sql="update existen set cantReal = cantReal + " +cantidadTXT.getText().toString() + " ,  pedido = pedido + " + cantidadTXT.getText().toString() + " where barcode ='" + barcode+ "' and talla = " + punto.getSelectedItem() + " and tienda = " + listado;
            st.executeUpdate(sql);
            st.close();

        } catch (Exception e) {
            Toast.makeText(getActivity(), "Error al finalizar el pedido", Toast.LENGTH_SHORT).show();
        }

    }




    public static void cargarDatos(String var){
        Principal.scannPass=true;
        scu=0;
        BarCodeFIN="";
        estiloBar="";
        VarEstilo="";

        sku=null;
        VarEstilo=var;
        buscarEnSku();
        validarSku();
        if(scu==0) {
            getBarcode();
        }
        getEstilo();
        if(estiloBar.equals("")){
            Toast.makeText(context, "Codigo de barras no existente en el sistema ", Toast.LENGTH_LONG).show();
        }
        sp2.setText(estiloBar);
        cargarDatosBarcode();

        llenarSp();


    }
    public static String getEstiloSimilar(String barcode){
        scu=0;
        String estilo="";
        try {
            Statement st = bdc.conexionBD(me.getServer(),me.getBase(),me.getUsuario(),me.getPass()).createStatement();
            ResultSet rs = st.executeQuery("select a.ESTILO from colores c inner join articulo a on c.numero = a.color where a.BARCODE='"+barcode+"'");


            while (rs.next()) {
                estilo=rs.getString(1);
            }


            st.close();


        } catch (SQLException e) {
            e.getMessage();
        }
        return estilo;
    }

    public static void cargarDatosBarcodeSimilar(String barcode){
        Principal.scannPass=true;
        Similar simi=new Similar();
        puntoBar=Double.parseDouble(simi.getPunto());
        try {
            Statement st = bdc.conexionBD(me.getServer(),me.getBase(),me.getUsuario(),me.getPass()).createStatement();
            String sql="select ac.acabado, co.color, ma.MARCA ,cor.Nombre from articulo a inner join acabados ac  on a.ACABADO=ac.numero inner join colores co on a.COLOR=co.numero inner join marcas ma on a.MARCA=ma.NUMERO inner join corridas cor on a.corrida=cor.id where a.barcode='"+barcode+"';";
            ResultSet rs = st.executeQuery(sql);


            while (rs.next()) {
                acabadoBar=rs.getString(1);
                colorBar=rs.getString(2);
                marcaBar=rs.getString(3);
                corridaBar=rs.getString(4);
            }
            st.close();
            String estilo=getEstiloSimilar(barcode);
            sp2.setText(estilo);
            llenarSp();
            //
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    public static void cargarDatosBarcode(){

        try {
            Statement st = bdc.conexionBD(me.getServer(),me.getBase(),me.getUsuario(),me.getPass()).createStatement();
            String sql="select ac.acabado, co.color, ma.MARCA ,cor.Nombre from articulo a inner join acabados ac  on a.ACABADO=ac.numero inner join colores co on a.COLOR=co.numero inner join marcas ma on a.MARCA=ma.NUMERO inner join corridas cor on a.corrida=cor.id where a.barcode='"+BarCodeFIN+"';";
            ResultSet rs = st.executeQuery(sql);


            while (rs.next()) {
                acabadoBar=rs.getString(1);
                colorBar=rs.getString(2);
                marcaBar=rs.getString(3);
                corridaBar=rs.getString(4);
            }
            st.close();

            //
        } catch (SQLException e) {
            e.getMessage();
        }
    }
    public void existenciasEnMiTienda(){


        try {

            Statement st = bdc.conexionBD(me.getServer(),me.getBase(),me.getUsuario(),me.getPass()).createStatement();
            String puntoSp=punto.getSelectedItem().toString();
            ResultSet rs = st.executeQuery("lupitaApartados'"+barcode+"',"+puntoSp+","+listado+",''");

            while(rs.next()) {
                existencias=(rs.getInt(2));
                existenciasTXT.setText(String.valueOf(existencias));
            }
            st.close();

        } catch (SQLException e1) {
            e1.printStackTrace();
            Toast.makeText(getActivity(),"No hay existencias " , Toast.LENGTH_LONG).show();
            try {
                Negar.start();
            }catch (IllegalThreadStateException e){
                e.getMessage();
            }



            existencias=0;
            existenciasTXT.setText("0");

        }


    }



    public void contarPuntos(){

        try {
            puntos.clear();
            puntos.add("");

            double inicio, fin, medio;
            inicio = Double.valueOf(in);
            fin = Double.valueOf(finn);
            medio = Double.valueOf(inc);

            for (double i = inicio; i < fin + medio; i = i + medio) {

                puntosCantidad++;

            }

        }catch (Exception e){

        }
    }



    public void llenarPuntos(){

        try {
            puntos.clear();
            puntos.add("");

            double inicio, fin, medio;
            inicio = Double.valueOf(in);
            fin = Double.valueOf(finn);
            medio = Double.valueOf(inc);

            for (double i = inicio; i < fin + medio; i = i + medio) {

                puntosCantidad++;
                puntos.add(i);
            }

        }catch (Exception e){

        }
    }


    public static int obtenerPosicionItem(Spinner spinner, String fruta) {
        //Creamos la variable posicion y lo inicializamos en 0
        int posicion = 0;
        //Recorre el spinner en busca del ítem que coincida con el parametro `String fruta`
        //que lo pasaremos posteriormente
        for (int i = 0; i < spinner.getCount(); i++) {
            //Almacena la posición del ítem que coincida con la búsqueda
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(fruta)) {
                posicion = i;
            }
        }
        //Devuelve un valor entero (si encontro una coincidencia devuelve la
        // posición 0 o N, de lo contrario devuelve 0 = posición inicial)
        return posicion;
    }

    public  static void llenarSp() {
        lista.add(null);
        lista.clear();
        String add="";
        lista.add("");
        try {
            Statement st = bdc.conexionBD(me.getServer(),me.getBase(),me.getUsuario(),me.getPass()).createStatement();
            String sql="select DISTINCT c.color, numero from colores c inner join articulo a on c.numero = a.color where a.estilo = '"+sp2.getText()+"'";
            ResultSet rs = st.executeQuery(sql);


            while (rs.next()) {
                add=rs.getString(1);
                colorCantidad ++;
                lista.add(add);
            }
            st.close();

            //
        } catch (Exception e) {

        }
    }

    public  static void contarSp() {
        lista.add(null);
        lista.clear();
        String add="";
        lista.add("");
        try {
            Statement st = bdc.conexionBD(me.getServer(),me.getBase(),me.getUsuario(),me.getPass()).createStatement();
            String sql="select DISTINCT c.color, numero from colores c inner join articulo a on c.numero = a.color where a.estilo = '"+sp2.getText()+"'";
            ResultSet rs = st.executeQuery(sql);


            while (rs.next()) {
                add=rs.getString(1);
                colorCantidad ++;
            }
            st.close();

            //
        } catch (Exception e) {

        }
    }



    public static String getColor(String color){

        try {
            Statement st = bdc.conexionBD(me.getServer(),me.getBase(),me.getUsuario(),me.getPass()).createStatement();
            String sql="SELECT numero FROM colores WHERE color='"+color+"'";
            ResultSet rs = st.executeQuery(sql);


            while (rs.next()) {
                Principal.idColor=rs.getString(1);
            }
            st.close();


        } catch (Exception e) {

        }

        return Principal.idColor;
    }


    public void llenarSp3() {
        listaAcabado.clear();
        listaAcabado.add("");
        Principal.idColor=getColor(spColor.getSelectedItem().toString());
        try {
            Statement st = bdc.conexionBD(me.getServer(),me.getBase(),me.getUsuario(),me.getPass()).createStatement();
            String sql="select DISTINCT ac.Acabado, numero from acabados ac inner join articulo a on ac.numero = a.acabado where a.estilo = '"+sp2.getText()+"' and a.Color = "+Principal.idColor+"";
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                acabadoCantidad++;
                listaAcabado.add(rs.getString(1));
            }
            st.close();


        } catch (Exception e) {
            Toast.makeText(getActivity(), "Error en sp3", Toast.LENGTH_SHORT).show();
        }

    }

    public String getAcabado(String acabado){
        try {
            Statement st = bdc.conexionBD(me.getServer(),me.getBase(),me.getUsuario(),me.getPass()).createStatement();
            String sql="SELECT numero FROM acabados WHERE acabado='"+acabado+"'";
            ResultSet rs = st.executeQuery(sql);


            while (rs.next()) {
                Principal.idAcabado=rs.getString(1);
            }
            st.close();


        } catch (Exception e) {

        }
        return Principal.idAcabado;
    }

    public void llenarSp4() {

        listaMarca.clear();
        listaMarca.add(" ");
        Principal.idAcabado=getAcabado(spAcabado.getSelectedItem().toString());
        try {
            Statement st = bdc.conexionBD(me.getServer(),me.getBase(),me.getUsuario(),me.getPass()).createStatement();
            String sql="select DISTINCT  m.marca, numero from marcas m inner join articulo a on m.numero = a.marca where a.estilo = '"+sp2.getText()+"' and a.Color = "+Principal.idColor+" and a.acabado = "+Principal.idAcabado+"";
            ResultSet rs = st.executeQuery(sql);


            while (rs.next()) {

                marcaCantidad++;
                listaMarca.add(rs.getString(1));
                spMarca.setId(rs.getInt(2));
            }
            st.close();


        } catch (Exception e) {
            Toast.makeText(getActivity(), "Error en sp4", Toast.LENGTH_SHORT).show();
        }

    }

    public String getMarca(String marca){
        try {
            Statement st = bdc.conexionBD(me.getServer(),me.getBase(),me.getUsuario(),me.getPass()).createStatement();
            String sql="SELECT numero FROM marcas WHERE marca='"+marca+"'";
            ResultSet rs = st.executeQuery(sql);


            while (rs.next()) {
                idMarca=rs.getString(1);
            }
            st.close();


        } catch (Exception e) {

        }
        return idMarca;
    }


    public void llenarSp5() {
        idMarca=getMarca(spMarca.getSelectedItem().toString());
        listaCorrida.clear();
        listaCorrida.add(" ");

        try {
            Statement st = bdc.conexionBD(me.getServer(),me.getBase(),me.getUsuario(),me.getPass()).createStatement();
            String sql="select  co.Nombre as Corrida,  co.id from corridas co inner join articulo a on co.id = a.corrida where a.estilo = '"+sp2.getText()+"' and a.Color = "+Principal.idColor+" and a.acabado = "+Principal.idAcabado+" and a.marca =" +idMarca+"";
            ResultSet rs = st.executeQuery(sql);


            while (rs.next()) {
                corridaCantidad++;
                listaCorrida.add(rs.getString(1));
                spCorrida.setId(rs.getInt(2));
            }
            st.close();


        } catch (Exception e) {
            Toast.makeText(getActivity(), "Error en sp5", Toast.LENGTH_SHORT).show();
        }

    }


    public void contarSp5() {
        idMarca=getMarca(spMarca.getSelectedItem().toString());
        listaCorrida.clear();


        try {
            Statement st = bdc.conexionBD(me.getServer(),me.getBase(),me.getUsuario(),me.getPass()).createStatement();
            String sql="select  co.Nombre as Corrida,  co.id from corridas co inner join articulo a on co.id = a.corrida where a.estilo = '"+sp2.getText()+"' and a.Color = "+Principal.idColor+" and a.acabado = "+Principal.idAcabado+" and a.marca =" +idMarca+"";
            ResultSet rs = st.executeQuery(sql);


            while (rs.next()) {
                corridaCantidad++;
            }
            st.close();


        } catch (Exception e) {
            Toast.makeText(getActivity(), "Error en sp5", Toast.LENGTH_SHORT).show();
        }

    }

    public void traerPrecio() {

        Principal.scannPass=false;
        try {
            Statement st = bdc.conexionBD(me.getServer(),me.getBase(),me.getUsuario(),me.getPass()).createStatement();
            String sql="select precio,  DESCTO from precios where BARCODE="+"'"+barcode+"' and talla="+punto.getSelectedItem();
            ResultSet rs = st.executeQuery(sql);


            while (rs.next()) {
                variableS=rs.getString(1);
                descuento=rs.getString(2);
                BigDecimal bigDecimal=new BigDecimal(Double.parseDouble(variableS)).setScale(2,RoundingMode.UP);
                double subtotal=bigDecimal.floatValue();
                precioTXT.setText(String.valueOf(subtotal));
                descuentoTXT.setText("%"+descuento);
                generarDescuento();
            }
            st.close();


        } catch (SQLException e) {
            e.getMessage();
            Toast.makeText(getActivity(), "Error en traer precio", Toast.LENGTH_SHORT).show();
        }

    }

    private void  generarDescuento() {

        double Total;
        auxiliarDes=Double.parseDouble(descuento)/100;
        descuentoFinal = Double.parseDouble(variableS)*auxiliarDes;
        BigDecimal bigDecimal=new BigDecimal(Double.parseDouble(variableS)- descuentoFinal).setScale(2,RoundingMode.UP);
        Total=bigDecimal.floatValue();
        totalTXT.setText("$"+String.valueOf(Total));

    }
    public void limpiarCajas(){
        existenciasTXT.setText(null);
        totalTXT.setText(null);
        descuentoTXT.setText(null);
        precioTXT.setText(null);
        totalTXT.setText(null);
        efectivoTxt.setText(null);



    }

    public void llenarTabla(){
        String idCorrida=getIdCorrida();
        where=" where ";
        if(buscador==0){
            where+=" a.estilo = '"+sp2.getText()+"' and a.Color ="+Principal.idColor+" and a.acabado ="+Principal.idAcabado+" and a.marca ="+idMarca+" and a.corrida="+idCorrida;
        }else if(buscador==1){
            where+=" a.estilo = '"+sp2.getText()+"' and a.marca ="+idMarca+" and a.corrida="+idCorrida;
        }

        try {
            Statement st = bdc.conexionBD(me.getServer(),me.getBase(),me.getUsuario(),me.getPass()).createStatement();

            String sql="select a.BARCODE,co.inicial,co.final,co.incremento,c.color,ma.marca,ac.acabado,im.id,a.UBICA,t.NUMERO,clas.NUMERO,sl.NUMERO,su.numero,ta.NUMERO,co.id\n" +
                    " from articulo a inner join lineas l on a.LINEA=l.NUMERO inner join sublinea sl on a.SUBLINEA=sl.NUMERO inner join temporad t on a.TEMPORAD=t.NUMERO\n" +
                    "  inner join proveed p on a.PROVEED=p.numero\n" +
                    "  left join empleado e on a.comprador=e.numero inner join departamentos d on a.DEPARTAMENTO=d.NUMERO\n" +
                    "  inner join tacones ta on a.TACON=ta.NUMERO inner join plantillas pl on a.PLANTILLA=pl.NUMERO inner join forros f on a.FORRO=f.NUMERO \n" +
                    "  inner join corridas co on a.corrida=co.id inner join suelas su on a.SUELA=su.numero inner join colores c on a.color = c.numero\n" +
                    "  inner join acabados ac on a.ACABADO=ac.NUMERO inner join marcas ma on a.MARCA=ma.NUMERO left join imagenes im on a.id=im.id inner join clasific clas on a.CLASIFIC=clas.numero "+ where ;
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {

                Similar simi=new Similar();
                barcode=rs.getString(1);
                in=rs.getString(2);
                finn=rs.getString(3);
                inc=rs.getString(4);
                color=rs.getString(5);
                marcas=rs.getString(6);
                acabado=rs.getString(7);
                idImagen=rs.getString(8);
                ubica=rs.getString(9);
                simi.setMarca(marcas);
                simi.setColor(color);
                simi.setAcabado(acabado);
                simi.setTemporada(rs.getString(10));
                simi.setClasificacion(rs.getString(11));
                simi.setSubLinea(rs.getString(12));
                simi.setSuela(rs.getString(13));
                simi.setTacon(rs.getString(14));
                simi.setCorrida(rs.getString(15));
            }
            st.close();


        } catch (Exception e) {
            e.getMessage();
            Toast.makeText(getActivity(), "", Toast.LENGTH_SHORT).show();
        }


    }



    public void ToolBarNombre(){
        try {
            Statement st = bdc.conexionBD(me.getServer(),me.getBase(),me.getUsuario(),me.getPass()).createStatement();
            String sql="select nombre from tiendas where numero="+listado;
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {

                NombreTienda=rs.getString(1);
            }
            st.close();


        } catch (Exception e) {
            e.getMessage();
            Toast.makeText(getActivity(), "Error en llenar encabezado", Toast.LENGTH_SHORT).show();
        }

    }








    public void getUser(){
        try {


            ModeloNumeroOrden mno=new ModeloNumeroOrden();
            mno.setUsuario(mu.getNombre());
            Statement st = bdc.conexionBD(me.getServer(),me.getBase(),me.getUsuario(),me.getPass()).createStatement();
            ResultSet rs=st.executeQuery("select numero, nombre from empleado where [user]='"+ mu.getNombre()+"'");

            while(rs.next()){
                numeroUsuario=rs.getString(1);
                NombreUsuario=(rs.getString( 2));
            }
            st.close();


        } catch (Exception e) {
            Toast.makeText(getActivity(), "Error al optener usuario", Toast.LENGTH_SHORT).show();
        }
    }




    public static void buscarEnSku(){

        try {
            Statement st = bdc.conexionBD(me.getServer(),me.getBase(),me.getUsuario(),me.getPass()).createStatement();
            String sql="SELECT saz FROM sku WHERE sku ='"+VarEstilo+"'";
            ResultSet rs = st.executeQuery(sql);


            while (rs.next()) {
                sku=(rs.getString(1));
            }
            st.close();

        } catch (SQLException xe) {
            xe.getMessage();

        }
    }

    public static void validarSku(){
        String predeterminada = "SALIDA";

        //declaramos una palabra de entrada
        String   entrada = sku;

        //variable usada para verificar si las palabras son iguales
        String aux="";

        //se verifica que ambas palabras tengan la misma longitud
        //si no es asi no se pueden comparar
        if(sku!=null) {
            configurarBarcodeSku();
            consultarPuntoBar();
            scu=1;

        }else{

            configurarBarcode();

        }





    }

    public static void configurarBarcodeSku(){
        String aux=sku;
        String[] barcode=aux.split("");
        int tamaño=barcode.length;
        for(int i=0;i<tamaño-3;i++){
            BarCodeFIN+=barcode[i];
        }
    }


    public static void configurarBarcode(){
        String aux=VarEstilo;
        String[] barcode=aux.split("");
        String res=""+barcode[9]+barcode[10]+barcode[11];
        Double resint=Double.parseDouble(res);
        puntoBar=resint/10;
        CodigoBar=barcode[0]+barcode[1]+barcode[2]+barcode[3]+barcode[4]+barcode[5]+barcode[6]+barcode[7]+barcode[8];
    }


    public static void consultarPuntoBar(){
        try {
            Statement st = bdc.conexionBD(me.getServer(),me.getBase(),me.getUsuario(),me.getPass()).createStatement();
            String sql="select  LEFT(saz,LEN(saz)-3) as barcode, cast(cast(RIGHT(saz,3)as decimal(4,1))/10 as decimal(4,1))  as talla from sku where sku = '"+VarEstilo+"'";
            ResultSet rs = st.executeQuery(sql);


            while (rs.next()) {
                puntoBar=rs.getDouble(2);
            }
            st.close();


        } catch (SQLException xe) {
            xe.getMessage();

        }
    }
    public static void getBarcode(){
        try {
            Statement st = bdc.conexionBD(me.getServer(),me.getBase(),me.getUsuario(),me.getPass()).createStatement();
            ResultSet rs = st.executeQuery("select p.barcode from precios p inner join articulo a on a.barcode = p.barcode where p.talla = "+puntoBar+" and a.id = "+CodigoBar);


            while (rs.next()) {
                BarCodeFIN=rs.getString(1);
            }
            st.close();


        } catch (SQLException xe) {
            xe.getMessage();

        }

    }

    public static void getEstilo(){
        scu=0;
        try {
            Statement st = bdc.conexionBD(me.getServer(),me.getBase(),me.getUsuario(),me.getPass()).createStatement();
            ResultSet rs = st.executeQuery("select a.ESTILO from colores c inner join articulo a on c.numero = a.color where a.BARCODE='"+BarCodeFIN+"'");

            while (rs.next()) {
                estiloBar=rs.getString(1);

            }
            st.close();


        } catch (SQLException e) {
            e.getMessage();
        }

    }


    public void contarSp3() {
        listaAcabado.clear();
        listaAcabado.add("");
        Principal.idColor=getColor(spColor.getSelectedItem().toString());
        try {
            Statement st = bdc.conexionBD(me.getServer(),me.getBase(),me.getUsuario(),me.getPass()).createStatement();
            String sql="select DISTINCT ac.Acabado, numero from acabados ac inner join articulo a on ac.numero = a.acabado where a.estilo = '"+sp2.getText()+"' and a.Color = "+Principal.idColor+"";
            ResultSet rs = st.executeQuery(sql);


            while (rs.next()) {
                acabadoCantidad++;
                String add=(rs.getString(1));
            }
            st.close();


        } catch (Exception e) {
            Toast.makeText(getActivity(), "Error en sp3", Toast.LENGTH_SHORT).show();
        }

    }




    public String ultimaVez(){
        String numeroT=null;

        try {
            ConexionSQLiteHelper conn = new ConexionSQLiteHelper(context, "db tienda", null, 1);
            SQLiteDatabase db = conn.getReadableDatabase();

            String sql="SELECT nombreT FROM tienda where id=1";
            Cursor cursor = db.rawQuery(sql, null);
            while (cursor.moveToNext()) {
                numeroT = (cursor.getString(0));
            }
        }catch (RuntimeException r){

        }catch (Exception e){

        }

        return numeroT;
    }




    public void ConsultarNuevosRegistros(){

        try {

            String tienda=ultimaVez();
            Statement st = bdc.conexionBD(me.getServer(),me.getBase(),me.getUsuario(),me.getPass()).createStatement();
            String sql="SELECT COUNT(numero) FROM comanderoDet where tienda="+tienda+" and [status]=1";
            ResultSet rs = st.executeQuery(sql);


            while (rs.next()) {
                cantidadRegistros=rs.getInt(1);
            }
            st.close();


        } catch (RuntimeException r){

        }catch (Exception e) {
            Toast.makeText(getActivity(),"",Toast.LENGTH_LONG).show();
        }
    }


    public String getIdCorrida(){
        String idCorrida=null;
        try {

            Statement st = bdc.conexionBD(me.getServer(),me.getBase(),me.getUsuario(),me.getPass()).createStatement();
            String sql=" select corridas.id from corridas  inner join articulo on  corridas.id=articulo.corrida  where estilo='"+sp2.getText()+"' and marca="+idMarca +"and nombre='"+spCorrida.getSelectedItem()+"'";
            ResultSet rs = st.executeQuery(sql);


            while (rs.next()) {
                idCorrida=rs.getString(1);
            }
            st.close();


        }catch (RuntimeException r){

        } catch (Exception e) {
            Toast.makeText(getActivity(),"************",Toast.LENGTH_LONG).show();
        }
        return  idCorrida;
    }







    public String getIdEmpresa(){
        String id="";
        try{
            Statement st= conex.conexionBD().createStatement();

            String sql="select idEmpresa from logins where Empresa='"+me.getEmpresa()+"'";
            ResultSet rs=st.executeQuery(sql);

            while(rs.next()){
                id=rs.getString(1);
            }
            st.close();
        }catch (Exception e ){
            e.getMessage();
            Toast.makeText(getActivity(), "No sé puede consultar el id de la empresa", Toast.LENGTH_SHORT).show();
        }
        return id;
    }


    public String obtenerCaja(){

        String caja="";
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(getActivity(), "db tienda", null, 1);
        SQLiteDatabase db = conn.getReadableDatabase();

        String sql="SELECT nombreCaja FROM caja where id=1 order by id desc";
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            caja=(cursor.getString(0));


        }

        return caja;
    }


    public String obtenerFecha(){
        String au=null;
        String fechas="";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmss", Locale.getDefault());

        Date date=new Date();
        String aux = dateFormat.format(date);
        String[] fecha=aux.split("");
        for(int i=1;i<fecha.length;i++){
            int auxiliar=Integer.parseInt(fecha[i]);
            int  x=0;
            for( x=0;x<10;x++){

                if(x==auxiliar){

                    au+="-"+x;




                }

            }
        }
        String[] a=au.split("-");
        fechas=a[3]+a[4]+a[5]+a[6]+a[7]+a[8];

        return fechas;

    }


    public String obtenerConcecutivo(){
        String conse="";
        String concecutiva="";

        try {

            Statement st = bdc.conexionBD(me.getServer(), me.getBase(), me.getUsuario(), me.getPass()).createStatement();
            String sql = "select MAX (ticket+1)  from fma";
            ResultSet rs=st.executeQuery(sql);

            while (rs.next()) {
                conse=rs.getString(1);
            }
            st.close();

            String [] splitConse=conse.split("");


            for(int i=15;i<splitConse.length;i++){

                concecutiva+=splitConse[i];
            }

        } catch (SQLException e){
            e.getMessage();
        }



        return concecutiva;
    }



    public String  generarTicket(){

        String caja=obtenerCaja();
        String fecha=obtenerFecha();
        String tienda=ultimaVez();
        String conse=obtenerConcecutivo();


        String ticket=caja+fecha+tienda+conse;
        return ticket;
    }


    public void obtenerBanco(){
        listaBancos.clear();

        spBanco.setAdapter(null);

        String tienda=ultimaVez();
        try {

            Statement st = bdc.conexionBD(me.getServer(), me.getBase(), me.getUsuario(), me.getPass()).createStatement();
            String sql = "select nombre from formaspago where tienda="+tienda+ " and status=1 and nombre <> 'vale de promotor' ";
            ResultSet rs=st.executeQuery(sql);

            while (rs.next()) {
                listaBancos.add(rs.getString(1));
            }
            st.close();


        } catch (SQLException e){
            e.getMessage();
        }
    }






    public String obtenerFormaPago(){
        String tienda=ultimaVez();
        String banco="";
        try {

            Statement st = bdc.conexionBD(me.getServer(), me.getBase(), me.getUsuario(), me.getPass()).createStatement();
            String sql = "select clave from formaspago where  nombre <> 'vale de promotor' and tienda="+tienda+" and status=1 and nombre='"+spBanco.getSelectedItem().toString();
            ResultSet rs=st.executeQuery(sql);

            while (rs.next()) {
                banco=rs.getString(1);
            }
            st.close();


        } catch (SQLException e){
            e.getMessage();
        }

        return banco;
    }



    public void validarPago(String ticket){


        try{
            String banco=obtenerFormaPago();

            if(banco.equals("D1")){
                d1=efectivoTxt.getText().toString();
                insertarFma(ticket,d1);

            }else if(banco.equals("D2")){
                d2=efectivoTxt.getText().toString();
                insertarFma(ticket,d2);

            }else if(banco.equals("D3")){
                d3=efectivoTxt.getText().toString();
                insertarFma(ticket,d3);

            }else if(banco.equals("D4")){
                d4=efectivoTxt.getText().toString();
                insertarFma(ticket,d4);

            }else if(banco.equals("D5")){
                d5=efectivoTxt.getText().toString();
                insertarFma(ticket,d5);

            }else if(banco.equals("D6")){
                d6=efectivoTxt.getText().toString();
                insertarFma(ticket,d6);

            }else if(banco.equals("D7")){
                d7=efectivoTxt.getText().toString();
                insertarFma(ticket,d7);

            }else if(banco.equals("D8")){
                d8=efectivoTxt.getText().toString();
                insertarFma(ticket,d8);

            }else if(banco.equals("D9")){
                d9=efectivoTxt.getText().toString();
                insertarFma(ticket,d9);

            }else if(banco.equals("D0")){
                d0=efectivoTxt.getText().toString();
                insertarFma(ticket,d0);

            }else if(banco.equals("VD")){
                vd=efectivoTxt.getText().toString();
                insertarFma(ticket,vd);

            }else if(banco.equals("CF")){
                cf=efectivoTxt.getText().toString();
                insertarFma(ticket,cf);

            }else if(banco.equals("VI")){
                vi=efectivoTxt.getText().toString();
                insertarFma(ticket,vi);

            }else if(banco.equals("E1")){
                e1=efectivoTxt.getText().toString();
                insertarFma(ticket,e1);

            }else if(banco.equals("E2")){
                e2=efectivoTxt.getText().toString();
                insertarFma(ticket,e2);

            }else if(banco.equals("E3")){
                e3=efectivoTxt.getText().toString();
                insertarFma(ticket,e3);

            }else if(banco.equals("E4")){
                e4=efectivoTxt.getText().toString();
                insertarFma(ticket,e4);

            }else if(banco.equals("E5")){
                e5=efectivoTxt.getText().toString();
                insertarFma(ticket,e5);

            }else if(banco.equals("E6")){
                e6=efectivoTxt.getText().toString();
                insertarFma(ticket,e6);

            }else if(banco.equals("E7")){
                e7=efectivoTxt.getText().toString();
                insertarFma(ticket,e7);

            }else if(banco.equals("E8")){
                e8=efectivoTxt.getText().toString();
                insertarFma(ticket,e8);

            }else if(banco.equals("E9")){
                e9=efectivoTxt.getText().toString();
                insertarFma(ticket,e9);

            }else if(banco.equals("E0")){
                e0=efectivoTxt.getText().toString();
                insertarFma(ticket,e0);
            }
        }catch (Exception e){
            Toast.makeText(getActivity(),"Error al validar pago: "+ e.getMessage(),Toast.LENGTH_LONG).show();
        }



    }





    public void updatePago(){
        try{
            ConexionSQLiteHelper conn = new ConexionSQLiteHelper(getActivity(), "db tienda", null, 1);
            SQLiteDatabase db = conn.getReadableDatabase();

            String sql="UPDATE pago set efectivo=efectivo + " +efectivoTxt.getText().toString() + " where formaPago like '%" +spBanco.getSelectedItem().toString()+"%'";
            db.execSQL(sql);


        }catch (Exception e){
            Toast.makeText(getActivity(),"Problema al guardar el pago error: "+e.getMessage(),Toast.LENGTH_LONG).show();

        }




    }

    public void mostrarPago(){

        try {
            Pago pago = null;
            ConexionSQLiteHelper conn = new ConexionSQLiteHelper(getActivity(), "db tienda", null, 1);
            SQLiteDatabase db = conn.getReadableDatabase();
            String sql = "SELECT * FROM pago";
            Cursor cr = db.rawQuery(sql, null);
            while (cr.moveToNext()) {

                pago= new Pago();
                String efec=cr.getString(1);
                String forma=cr.getString(2);
                pago.setFormaPago(forma);
                pago.setEfectivo(efec);

                listaPago.add(pago);

            }
        }catch (Exception e){
            e.getMessage();
        }
    }


    public void registroPagos(){

        try {

            ConexionSQLiteHelper conn = new ConexionSQLiteHelper(getActivity(), "db tienda", null, 1);
            SQLiteDatabase db = conn.getReadableDatabase();
            String sql = "SELECT * FROM pago  where formaPago like '%"+spBanco.getSelectedItem().toString()+"%'";
            Cursor cr = db.rawQuery(sql, null);
            while (cr.moveToNext()) {

                metodoPago=(cr.getString(2));
                efectivoPagado=cr.getDouble(1);


            }
        }catch (Exception e){
            e.getMessage();
        }
    }

    public void dropDatos(){
        try {
            ConexionSQLiteHelper conn = new ConexionSQLiteHelper(getActivity(), "db tienda", null, 1);
            SQLiteDatabase db = conn.getReadableDatabase();
            String sql = "DELETE FROM  pago";
            db.execSQL(sql);

        }catch (Exception e){
            e.getMessage();
        }
    }

    public void delete(){
        try {
            ConexionSQLiteHelper conn = new ConexionSQLiteHelper(getActivity(), "db tienda", null, 1);
            SQLiteDatabase db = conn.getReadableDatabase();
            String sql = "DELETE FROM pedido";
            db.execSQL(sql);



        }catch (Exception e){
            e.getMessage();
        }
    }


    private ArrayList<String[]>getProductos(){
        ArrayList<String[]>rows=new ArrayList<>();

        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(getActivity(), "db tienda", null, 1);
        SQLiteDatabase db = conn.getReadableDatabase();


        Cursor cr = db.rawQuery("SELECT cantidad, estilo,color,marca,talla,descuento,total FROM " + Utilidades.TABLA_CONTENEDOR , null);
        while (cr.moveToNext()) {

            rows.add(new String[] {cr.getString(0),cr.getString(1)+" "+cr.getString(2)+" "+cr.getString(3)+" "+cr.getString(4),cr.getString(5),"$"+cr.getString(6)});

        }


        return rows;
    }

    private ArrayList<String[]>getPagos(){

            ArrayList<String[]>rows=new ArrayList<>();
            ConexionSQLiteHelper conn = new ConexionSQLiteHelper(getActivity(), "db tienda", null, 1);
            SQLiteDatabase db = conn.getReadableDatabase();


            Cursor cr = db.rawQuery("SELECT efectivo, formaPago FROM " + Utilidades.TABLA_PAGO , null);
            while (cr.moveToNext()) {

                String pago=cr.getString(0);
                String forma=cr.getString(1);

                rows.add(new String[] {forma,"$"+pago});

            }

        DecimalFormat formato= new DecimalFormat("#.##");
        rows.add(new String[] {"Cambio: ","$"+formato.format(cambio)});
            return rows;

    }

    private ArrayList<String[]>getSubTotal(){
        ArrayList<String[]>rows=new ArrayList<>();
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(getActivity(), "db tienda", null, 1);
        SQLiteDatabase db = conn.getReadableDatabase();


        Cursor cr = db.rawQuery("SELECT SUM(cantidad),SUM(total) FROM " + Utilidades.TABLA_CONTENEDOR , null);
        while (cr.moveToNext()) {
            DecimalFormat df = new DecimalFormat("#.00");



           rows.add(new String[] { " "," ","SubTotal: $"+df.format(subtotalTicket=Double.parseDouble(cr.getString(1))/1.16)});

        }
        return rows;
    }

    private ArrayList<String[]>getTotal(){
        ArrayList<String[]>rows=new ArrayList<>();
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(getActivity(), "db tienda", null, 1);
        SQLiteDatabase db = conn.getReadableDatabase();

        Cursor cr = db.rawQuery("SELECT SUM(cantidad),SUM(total) FROM " + Utilidades.TABLA_CONTENEDOR , null);
        while (cr.moveToNext()) {
            rows.add(new String[] {" Pares: "+cr.getString(0) ," ","Total: $"+cr.getString(1)});
        }
        return rows;
    }


    private ArrayList<String[]>getIva(){
        ArrayList<String[]>rows=new ArrayList<>();
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(getActivity(), "db tienda", null, 1);
        SQLiteDatabase db = conn.getReadableDatabase();
        DecimalFormat df = new DecimalFormat("#.00");


        Cursor cr = db.rawQuery("SELECT SUM(cantidad),SUM(total) FROM " + Utilidades.TABLA_CONTENEDOR , null);
        while (cr.moveToNext()) {

            double iva=subtotalTicket*0.16;

            rows.add(new String[] { "  "," ","IVA: $"+df.format(iva)});

        }
        return rows;
    }


    public void obtenerDatosTienda(){

        try {

            Statement st = bdc.conexionBD(me.getServer(), me.getBase(), me.getUsuario(), me.getPass()).createStatement();
            String sql = "select calle,tel,ciudad,estado,cp,idColonia from tiendas where numero="+mt.getNombreTienda();
            ResultSet rs=st.executeQuery(sql);

            while (rs.next()) {
                calle=rs.getString(1);
                tel=rs.getString(2);
                ciudad=rs.getString(3);
                estado=rs.getString(4);
                cp=rs.getString(5);
                idColonia=rs.getString(6);
            }
            st.close();



        } catch (SQLException e){
            e.getMessage();
        }
    }

    public String obtenerColonia(){
        String colonia="";
        try {

            Statement st = bdc.conexionBD(me.getServer(), me.getBase(), me.getUsuario(), me.getPass()).createStatement();
            String sql = "select colonia from sepomex where id="+idColonia;
            ResultSet rs=st.executeQuery(sql);

            while (rs.next()) {
                colonia=rs.getString(1);

            }
            st.close();



        } catch (SQLException e){
            e.getMessage();
        }

        return colonia;
    }

    public void descontarExistencias(){
        try {
            Statement st = bdc.conexionBD(me.getServer(), me.getBase(), me.getUsuario(), me.getPass()).createStatement();
            String sql="update existen set cantReal= cantReal + " +cantidadTXT.getText().toString() + " ,  pedido = pedido + " + cantidadTXT.getText().toString() + " where barcode ='" + barcode+ "' and talla = " + punto.getSelectedItem() + " and tienda = " + listado;
            st.executeUpdate(sql);
            st.close();

        } catch (Exception e) {
            Toast.makeText(getActivity(), "Error al finalizar el pedido", Toast.LENGTH_SHORT).show();
        }
    }


    public void razonSocial(){
        try {
            String tienda=ultimaVez();
            Statement st = bdc.conexionBD(me.getServer(), me.getBase(), me.getUsuario(), me.getPass()).createStatement();
            String sql="select  razones.nombre, rfc, getDate() from tiendas inner join razones on tiendas.RAZON=razones.ID_RAZON where tiendas.numero ="+tienda;
            ResultSet rs=st.executeQuery(sql);
            while(rs.next()){

                razon=rs.getString(1);
                rfc=rs.getString(2);
                fechaTicket=rs.getString(3);
            }
            st.close();

        } catch (Exception e) {
            Toast.makeText(getActivity(), "Error al finalizar el pedido", Toast.LENGTH_SHORT).show();
        }
    }

    public void getSocio(){
        try {
            listaSocio.add(" VENTAS MOSTRADOR");
            String tienda=ultimaVez();
            Statement st = bdc.conexionBD(me.getServer(), me.getBase(), me.getUsuario(), me.getPass()).createStatement();
            String sql="select nombre from socios where ESTATUS=1";
            ResultSet rs=st.executeQuery(sql);

            while(rs.next()){

                listaSocio.add(rs.getString(1));
            }
            st.close();

        } catch (Exception e) {
            Toast.makeText(getActivity(), "Error al obtener socios ", Toast.LENGTH_SHORT).show();
        }
    }
    public void iva(){

        try {
            ConexionSQLiteHelper conn = new ConexionSQLiteHelper(getActivity(), "db tienda", null, 1);
            SQLiteDatabase db = conn.getReadableDatabase();

            String sql="SELECT "+Utilidades.CAMPO_IVA+" FROM "+Utilidades.TABLA_CHECKI;
            Cursor cursor = db.rawQuery(sql, null);
            while (cursor.moveToNext()) {
                iva = Integer.parseInt(cursor.getString(0));
            }
        }catch (Exception e){
            Toast toast = Toast.makeText(getActivity(), "La versión nueva de SazMobile POS se ha instalado", Toast.LENGTH_LONG);
            TextView x = (TextView) toast.getView().findViewById(android.R.id.message);
            x.setTextColor(Color.YELLOW); toast.show();
            Intent intent = new Intent(getActivity(), Principal.class);
            startActivity(intent);
            getActivity().deleteDatabase("db tienda");
        } finally {

        }





    }




    public  void buscarMarcas() {
        listaMarca.add(null);
        listaMarca.clear();
        String add="";
        listaMarca.add("");
        try {
            Statement st = bdc.conexionBD(me.getServer(),me.getBase(),me.getUsuario(),me.getPass()).createStatement();
            String sql="select DISTINCT  marcas.MARCA,articulo.MARCA   from articulo inner join marcas on articulo.MARCA=marcas.NUMERO where estilo='"+sp2.getText()+"' ";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                add=rs.getString(1);
                marcaCantidad ++;
                listaMarca.add(add);
            }
            st.close();

            //
        } catch (Exception e) {

        }



    }

    public void obtenerCorrida() {
        idMarca=getMarca(spMarca.getSelectedItem().toString());
        listaCorrida.clear();
        listaCorrida.add(" ");

        try {
            Statement st = bdc.conexionBD(me.getServer(),me.getBase(),me.getUsuario(),me.getPass()).createStatement();
            String sql="select DISTINCT  co.Nombre as Corrida,  co.id from corridas co inner join articulo a on co.id = a.corrida where a.estilo = '"+sp2.getText()+"' and  a.marca =" +idMarca+"";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {

                corridaCantidad++;
                listaCorrida.add(rs.getString(1));
                spCorrida.setId(rs.getInt(2));
            }
            st.close();


        } catch (Exception e) {
            Toast.makeText(getActivity(), "Error en sp5", Toast.LENGTH_SHORT).show();
        }

    }


    public void traerDatosProducto(){
        String idCorrida=getIdCorrida();

        try {
            Statement st = bdc.conexionBD(me.getServer(),me.getBase(),me.getUsuario(),me.getPass()).createStatement();

            String sql="select a.BARCODE,co.inicial,co.final,co.incremento,c.color,ma.marca,ac.acabado,im.id,a.UBICA\n" +
                    " from articulo a inner join lineas l on a.LINEA=l.NUMERO inner join sublinea sl on a.SUBLINEA=sl.NUMERO inner join temporad t on a.TEMPORAD=t.NUMERO\n" +
                    "  inner join proveed p on a.PROVEED=p.numero\n" +
                    "  left join empleado e on a.comprador=e.numero inner join departamentos d on a.DEPARTAMENTO=d.NUMERO\n" +
                    "  inner join tacones ta on a.TACON=ta.NUMERO inner join plantillas pl on a.PLANTILLA=pl.NUMERO inner join forros f on a.FORRO=f.NUMERO \n" +
                    "  inner join corridas co on a.corrida=co.id inner join suelas su on a.SUELA=su.numero inner join colores c on a.color = c.numero\n" +
                    "  inner join acabados ac on a.ACABADO=ac.NUMERO inner join marcas ma on a.MARCA=ma.NUMERO left join imagenes im on a.id=im.id where a.estilo = '"+sp2.getText()+"' and a.marca = "+idMarca+" and a.corrida="+idCorrida;
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {


                barcode=rs.getString(1);
                in=rs.getString(2);
                finn=rs.getString(3);
                inc=rs.getString(4);
                color=rs.getString(5);
                marcas=rs.getString(6);
                acabado=rs.getString(7);
                idImagen=rs.getString(8);
                ubica=rs.getString(9);
            }
            st.close();

        } catch (Exception e) {
            Toast.makeText(getActivity(), "Error en llenar Tabla", Toast.LENGTH_SHORT).show();
        }

    }
    public void insertarFma(String ticket, String pago){

        String tienda=ultimaVez();
        String caja=obtenerCaja();
        try {

            Statement st = bdc.conexionBD(me.getServer(), me.getBase(), me.getUsuario(), me.getPass()).createStatement();
            String sql = "insert into fma (ticket,total,efectivo,fecha,tienda,caja,socio,pagare,idmov,d1,d2,d3,d4,d5,d6,d7,d8,d9,d0,vd,cf,vi,e1,e2,e3,e4,e5,e6,e7,e8,e9,e0,llave) values ("+ticket+","+importeTXT.getText()+","+efectivoTxt.getText()+", getDATE(),"+tienda+","+caja+",-1,"+ticket+","+ticket+","+d1+","+d2+","+d3+","+d4+","+d5+","+d6+","+d7+","+d8+","+d9+","+d0+","+vd+","+cf+","+vi+","+e1+","+e2+","+e3+","+e4+","+e5+","+e6+","+e7+","+e8+","+e9+","+e0+", newID());";
            ResultSet rs=st.executeQuery(sql);

            while (rs.next()) {

                listaBancos.add(rs.getString(1));

            }
            st.close();


        } catch (SQLException e){
            e.getMessage();
        }
    }

    public void insertarPago(){

        try{
            ConexionSQLiteHelper conn = new ConexionSQLiteHelper(getActivity(), "db tienda", null, 1);
            SQLiteDatabase db = conn.getReadableDatabase();

            String sql="Insert into pago (efectivo, formaPago) values ('"+efectivoTxt.getText()+"','"+spBanco.getSelectedItem()+"')";
            db.execSQL(sql);


        }catch (Exception e){
            Toast.makeText(getActivity(),"Problema al guardar el pago error: "+e.getMessage(),Toast.LENGTH_LONG).show();

        }

    }






    Thread Negar= new Thread(new Runnable() {
        @Override
        public void run() {



          String date="";

            try {

                Statement st = bdc.conexionBD(me.getServer(), me.getBase(), me.getUsuario(), me.getPass()).createStatement();
                String sql = "Select getDate()";
                ResultSet rs=st.executeQuery(sql);

                while(rs.next()){
                    date=rs.getString(1);
                }
                st.close();



            } catch (SQLException e){

            }
            String aux=date;
            String[] data=aux.split(" ");
            String puntoSp=punto.getSelectedItem().toString();
            String fecha=data[0];


            try {

                Statement st = bdc.conexionBD(me.getServer(), me.getBase(), me.getUsuario(), me.getPass()).createStatement();
                String sql = "insert into artneg (SOCIO,PUNTO, CANTIDAD, BARCODE,llave,fecha,tienda) VALUES (-1,"+puntoSp+",1,'"+barcode+"',NEWID(),'"+fecha+"',"+ultimaVez()+");";
                st.executeUpdate(sql);
                st.close();



            } catch (SQLException e){
                e.getMessage();
            }

        }
    });

    Thread insertarRegistroIgual= new Thread(new Runnable() {
        @Override
        public void run() {
            ConexionSQLiteHelper conn = new ConexionSQLiteHelper(context, "db tienda", null, 1);
            SQLiteDatabase db=conn.getWritableDatabase();




            db.execSQL("UPDATE contenedor set cantidad=cantidad + " + cantidadTXT.getText().toString()+", total=total +"+pre+ " WHERE id = "+idContenedor);

            db.close();

            pre=0;

        }
    });





    Thread mismoDispositivo= new Thread(new Runnable() {
        @Override
        public void run() {
            String modelo = Build.MODEL;
            String serie = Build.MANUFACTURER;
            String marca = Build.ID;
            try {
                Statement st = conex.conexionBD().createStatement();

                String sql = "SELECT idDisp FROM smAppAccesos where mail='" + mu.getCorreo() + "' and idempresa=" + getIdEmpresa() + " and app=2";
                ResultSet rs = st.executeQuery(sql);

                while (rs.next()) {
                    dispositivo = rs.getString(1);
                }
                st.close();

            } catch (Exception e) {
                e.getMessage();
                Toast.makeText(getActivity(), "No sé puede actualizar el dispositivo", Toast.LENGTH_SHORT).show();
            }


            String predeterminada = dispositivo;

            String entrada = marca + "-" + serie + "-" + modelo;


            if(predeterminada.equals(entrada)){

            }else{
                android.app.AlertDialog.Builder alerta = new android.app.AlertDialog.Builder(getActivity());
                alerta.setMessage("Se ha cerrado la sesión actual ya que otro usuario ha accedido con tus datos en otro dispositivo.")
                        .setCancelable(false).setIcon(R.drawable.aviso)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(getActivity(), Principal.class);
                                startActivity(intent);
                            }
                        });

                android.app.AlertDialog titulo = alerta.create();
                titulo.setTitle("Aviso");
                titulo.show();
            }



        }
    });


    Thread insertarPedido= new Thread(new Runnable() {
        @Override
        public void run() {
            ConexionSQLiteHelper conn = new ConexionSQLiteHelper(context, "db tienda", null, 1);
            SQLiteDatabase db=conn.getWritableDatabase();
            String sql="INSERT INTO  contenedor (estilo, imagen, talla, cantidad, marca, color, sub, total, barcode,acabado,corrida, cliente,ubicacion, descuento) VALUES('"+sp2.getText().toString()+"', '"+idImagen+"', '"+punto.getSelectedItem()+"', '"+cantidadTXT.getText().toString()+"','"+spMarca.getSelectedItem()+"','"+color+"','"+variableS+"','"+pre+"','"+barcode+"','"+acabado+"','"+spCorrida.getSelectedItem()+"','"+clienteTXT.getText()+"','"+ubica+"', '"+descuento+"')";
            db.execSQL(sql);
            db.close();
            pre=0;

        }
    });






    Thread formatoFecha= new Thread(new Runnable() {
        @Override
        public void run() {

            String au=null;

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmss", Locale.getDefault());

            Date date=new Date();
            String aux = dateFormat.format(date);
            String[] fecha=aux.split("");
            for(int i=1;i<fecha.length;i++){
                int auxiliar=Integer.parseInt(fecha[i]);
                int  x=0;
                for( x=0;x<10;x++){

                    if(x==auxiliar){

                        au+="-"+x;

                    }

                }
            }
            String[] a=au.split("-");
            idFecha=a[1]+a[2]+a[3]+a[4]+a[5]+a[6]+a[7]+a[8]+a[9]+a[10]+a[11]+a[12]+a[13]+a[14];
        }
    });



    Thread modoBusqueda = new Thread(new Runnable() {
        @Override
        public void run() {

            try {
                ConexionSQLiteHelper conn = new ConexionSQLiteHelper(getActivity(), "db tienda", null, 1);
                SQLiteDatabase db = conn.getReadableDatabase();

                String sql="SELECT "+Utilidades.CAMPO_BUSCADOR+" FROM "+Utilidades.TABLA_CHECKB;
                Cursor cursor = db.rawQuery(sql, null);
                while (cursor.moveToNext()) {
                    buscador= Integer.parseInt(cursor.getString(0));
                }
            }catch (Exception e){

            } finally {

            }

        }
    });






}
