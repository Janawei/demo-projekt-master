package de.ostfalia.se.lampe;

import de.ostfalia.se.interfaces.ILamp;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import javax.ws.rs.client.*;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import java.awt.*;
import java.io.IOException;
import java.util.Locale;
import de.ostfalia.se.interfaces.ILamp;
import org.primefaces.component.colorpicker.ColorPicker;
import org.primefaces.component.inputtext.InputText;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.faces.event.AjaxBehaviorEvent;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import java.awt.*;
import java.io.IOException;
import javax.inject.Named;
import java.awt.Color;

@Named
@ApplicationScoped
public class Java2NodeRedLampAdapter implements ILamp {
   private final String baseURL = "http://172.28.20.10:1880/lightControl/";
    protected Client client = ClientBuilder.newClient();

    private String name;
    private Color curlor = Color.RED;
    private boolean isOn;
    private float curthness;

    private String hex;


    @Override
    public void switchOn() throws IOException {
        WebTarget target = client.target(baseURL+"on");
        SyncInvoker request =  target.request(MediaType.APPLICATION_JSON_TYPE, MediaType.APPLICATION_OCTET_STREAM_TYPE, MediaType.TEXT_PLAIN_TYPE).acceptEncoding("UTF-8").acceptLanguage(Locale.GERMANY, Locale.GERMAN, Locale.US)  ;
        request.post(Entity.entity(new Form(), MediaType.TEXT_PLAIN));
        isOn = true;
    }


    @Override
    public void switchOn(float intensity) throws IOException {
        WebTarget target = client.target(baseURL+"on/" + intensity);
        SyncInvoker request =  target.request(MediaType.APPLICATION_JSON_TYPE, MediaType.APPLICATION_OCTET_STREAM_TYPE, MediaType.TEXT_PLAIN_TYPE).acceptEncoding("UTF-8").acceptLanguage(Locale.GERMANY, Locale.GERMAN, Locale.US)  ;
        request.post(Entity.entity(new Form(), MediaType.TEXT_PLAIN));
        isOn = true;
        curthness = intensity;
    }


    public void switchOn(Color color) throws IOException {
        WebTarget target = client.target(baseURL+"on/" + color);
        SyncInvoker request =  target.request(MediaType.APPLICATION_JSON_TYPE, MediaType.APPLICATION_OCTET_STREAM_TYPE, MediaType.TEXT_PLAIN_TYPE).acceptEncoding("UTF-8").acceptLanguage(Locale.GERMANY, Locale.GERMAN, Locale.US)  ;
        request.post(Entity.entity(new Form(), MediaType.TEXT_PLAIN));
        curlor = color;
    }

    @Override
    public void switchOff() throws IOException {
        WebTarget target = client.target(baseURL+"off");
        SyncInvoker request =  target.request(MediaType.APPLICATION_JSON_TYPE, MediaType.APPLICATION_OCTET_STREAM_TYPE, MediaType.TEXT_PLAIN_TYPE).acceptEncoding("UTF-8").acceptLanguage(Locale.GERMANY, Locale.GERMAN, Locale.US)  ;
        request.post(Entity.entity(new Form(), MediaType.TEXT_PLAIN));
        isOn = false;
    }

    @Override
    public void setColor(Color color) throws IOException {
        if (color == Color.BLUE) {
            WebTarget target = client.target(baseURL + "color/blau");
            SyncInvoker request = target.request(MediaType.APPLICATION_JSON_TYPE, MediaType.APPLICATION_OCTET_STREAM_TYPE, MediaType.TEXT_PLAIN_TYPE).acceptEncoding("UTF-8").acceptLanguage(Locale.GERMANY, Locale.GERMAN, Locale.US);

            request.post(Entity.entity(new Form(), MediaType.TEXT_PLAIN));
            curlor = color;
        } else if (color == Color.RED) {
            WebTarget target = client.target(baseURL + "color/rot");
            SyncInvoker request = target.request(MediaType.APPLICATION_JSON_TYPE, MediaType.APPLICATION_OCTET_STREAM_TYPE, MediaType.TEXT_PLAIN_TYPE).acceptEncoding("UTF-8").acceptLanguage(Locale.GERMANY, Locale.GERMAN, Locale.US);

            request.post(Entity.entity(new Form(), MediaType.TEXT_PLAIN));
            curlor = color;
        } else if (Color.GREEN.equals(color)) {
            WebTarget target = client.target(baseURL + "color/gruen");
            SyncInvoker request = target.request(MediaType.APPLICATION_JSON_TYPE, MediaType.APPLICATION_OCTET_STREAM_TYPE, MediaType.TEXT_PLAIN_TYPE).acceptEncoding("UTF-8").acceptLanguage(Locale.GERMANY, Locale.GERMAN, Locale.US);

            request.post(Entity.entity(new Form(), MediaType.TEXT_PLAIN));
            curlor = color;
        } else if (Color.MAGENTA.equals(color)) {
            WebTarget target = client.target(baseURL + "color/lila");
            SyncInvoker request = target.request(MediaType.APPLICATION_JSON_TYPE, MediaType.APPLICATION_OCTET_STREAM_TYPE, MediaType.TEXT_PLAIN_TYPE).acceptEncoding("UTF-8").acceptLanguage(Locale.GERMANY, Locale.GERMAN, Locale.US);

            request.post(Entity.entity(new Form(), MediaType.TEXT_PLAIN));
            curlor = color;
        }
    }

    @Override
    public void setIntensity(float intensity) throws IOException {
        WebTarget target = client.target(baseURL+"brightness/" + intensity);
        SyncInvoker request =  target.request(MediaType.APPLICATION_JSON_TYPE, MediaType.APPLICATION_OCTET_STREAM_TYPE, MediaType.TEXT_PLAIN_TYPE).acceptEncoding("UTF-8").acceptLanguage(Locale.GERMANY, Locale.GERMAN, Locale.US)  ;
        request.post(Entity.entity(new Form(), MediaType.TEXT_PLAIN));
        curthness = intensity;
    }

    @Override
    public Color getColor() throws IOException {
        return curlor;
    }

    @Override
    public float getIntensity() throws IOException {
        return curthness;
    }

    @Override
    public boolean getState() throws IOException {
        return isOn;
    }

    public void setColorHelp(String farbe) throws IOException{
        String penis = farbe;

        System.out.println("wtf" + penis);




        switch(farbe) {
            case "Blau":

                try {
                    setColor(Color.BLUE);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "Rot":
                try {
                    setColor(Color.RED);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "Grün":
                try {
                    setColor(Color.GREEN);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "Lila":
                try {
                    setColor(Color.MAGENTA);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                break;
            default:
                // code block
        }


    }
    //public static float hexToHue(String hex) {
        // Convert the hexadecimal color code to an RGB color
        //Color color = Color.decode(hex);
        //float[] hsbValues = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null);

        // Return the hue value in degrees
        //return hsbValues[0] * 360;
    //}


}
