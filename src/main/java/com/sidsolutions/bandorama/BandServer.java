package com.sidsolutions.bandorama;

import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.CookieParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.NewCookie;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/bandServer")
public class BandServer {

    static ArrayList<BandUser> bandUsers = new ArrayList<BandUser>();
    static ArrayList<Instrument> instruments = new ArrayList<Instrument>();
    static String[] clarinetMouthpieces = {"Yamaha 4C", "Yamaha 5c", "Vandoren B50", "Vandoren RL40" , "Selmer Focus"};
    static String[] saxophoneMouthpieces = {"Stock" , "Yamaha Custom EX" , "Mark IV prestige", "Jody Jazz Titanium", "Selmer C Star" };
    
    final static String COOKIE_NAME = "sessionCookie";

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/newuser")
    public Response createUser(LoginJson userJson) {
        if (repetitiveUser(userJson) == false) {
            BandUser user = new BandUser(userJson.getUsername(), userJson.getPassword());
            bandUsers.add(user);
            return Response.ok(user).build();
        }
        return Response.status(Status.NOT_ACCEPTABLE).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/CheckLogin")
    public Response validateLogin(LoginJson userjson) {
        for (BandUser currentUser : bandUsers) {
            if (currentUser.username.equalsIgnoreCase(userjson.username)) {
                if (currentUser.password.equals(userjson.password)) {
                    String secret = userjson.username;
                    NewCookie cookie = new NewCookie.Builder(COOKIE_NAME).value(secret).build();
                    return Response.ok(new ResponseStatus(true)).cookie(cookie).build();
                } else {
                    return Response.status(Status.UNAUTHORIZED).build();
                }
            }

        }
        return Response.status(Status.UNAUTHORIZED).build();

    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/processEquipment")
    public ResponseStatus processEquipment(EquipmentJson equipJson, @CookieParam(COOKIE_NAME) String userLoggedIn) {
        if (userLoggedIn != null) {
            for (BandUser currentUser : bandUsers) {
                if (currentUser.username.equalsIgnoreCase(userLoggedIn)) {
                    currentUser.setEquipJson(equipJson);
                    return new ResponseStatus(true);
                }
            }
        }
        return new ResponseStatus(false);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getEquipment")
    public Response getEquipment(@CookieParam(COOKIE_NAME) String userLoggedIn) {
        if (userLoggedIn != null) {
            for (BandUser currentUser : bandUsers) {
                if (currentUser.username.equalsIgnoreCase(userLoggedIn)) {
                    return Response.ok(currentUser.getEquipJson()).build();
                }
            }
        }
        return Response.status(Status.UNAUTHORIZED).build();
    }

    public boolean repetitiveUser(LoginJson userjson) {
        for (BandUser currentUser : bandUsers) {
            if (currentUser.username.equals(userjson.username)) {
                return true;
            }
            return false;
        }
        return false;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/newpassword")
    public Response changePassword(LoginJson userJson) {
        for (BandUser currentUser : bandUsers) {
            if (currentUser.username.equals(userJson.username)) {
                currentUser.password = userJson.password;
                return Response.ok(currentUser).build();
            }
        }
        return Response.status(Status.NOT_ACCEPTABLE).build();
    }

    public static void addInstruments() {
        instruments.add(new Instrument("Flute",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSclQcWMM-1vHvKOQUM-RLbJe9clq8eXFppOA&usqp=CAU"));
        instruments.add(new Instrument("Clarinet", "https://static.wixstatic.com/media/84fb4e_2c399e78aa7e446195472d3e509e0497~mv2.png/v1/fill/w_625,h_625,al_c,q_90,usm_0.66_1.00_0.01,enc_auto/84fb4e_2c399e78aa7e446195472d3e509e0497~mv2.png"));
        instruments.add(new Instrument("Saxophone", "https://jeanpaulusa.com/cdn/shop/products/as-400_20dg-02.jpg?v=1699706544&width=1800"));
        // instruments.add("https://www.google.com/imgres?imgurl=https%3A%2F%2Fmedia.sweetwater.com%2Fm%2Fproducts%2Fimage%2Fac23c66f9emramJaWtP4LviHC0FM3WODZzsha6u4.jpg%3Fquality%3D82%26width%3D750%26ha%3Dac23c66f9e21b2eb&tbnid=4x3WO-yMG5zDNM&vet=12ahUKEwjzwsSlmJ-DAxXGyMkDHRhsDGYQMygBegUIARCdAQ..i&imgrefurl=https%3A%2F%2Fwww.sweetwater.com%2Fstore%2Fdetail%2FYTR2330--yamaha-ytr-2330-student-bb-trumpet-gold-lacquer&docid=lWuIqwNvqlq19M&w=750&h=333&q=trumpet&hl=en&ved=2ahUKEwjzwsSlmJ-DAxXGyMkDHRhsDGYQMygBegUIARCdAQ");
        // instruments.add("https://www.google.com/imgres?imgurl=https%3A%2F%2Fupload.wikimedia.org%2Fwikipedia%2Fcommons%2Fthumb%2F7%2F77%2FYamaha_Horn_YHR-667V.tif%2Flossy-page1-1200px-Yamaha_Horn_YHR-667V.tif.jpg&tbnid=2n6Lp5JOSCuKvM&vet=12ahUKEwjn8oewmJ-DAxVpFdAFHRTYBBoQMygAegUIARCQAQ..i&imgrefurl=https%3A%2F%2Fen.wikipedia.org%2Fwiki%2FFrench_horn&docid=Wqk3wC3VYH0hwM&w=1200&h=770&q=french%20horn&hl=en&ved=2ahUKEwjn8oewmJ-DAxVpFdAFHRTYBBoQMygAegUIARCQAQ");
        // instruments.add("https://www.google.com/imgres?imgurl=http%3A%2F%2Fus.wessex-tubas.com%2Fcdn%2Fshop%2Fproducts%2FTC236GL_0168-min.jpg%3Fv%3D1624549836&tbnid=LlrH8l2_9yxCOM&vet=12ahUKEwir9rPLmJ-DAxWzx8kDHV1RD4EQMygJegQIARB_..i&imgrefurl=https%3A%2F%2Fus.wessex-tubas.com%2Fproducts%2Ffrench-c-tuba-tc236&docid=vxkNDsmhjyX1kM&w=1211&h=1211&q=tuba&hl=en&ved=2ahUKEwir9rPLmJ-DAxWzx8kDHV1RD4EQMygJegQIARB_");
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getInstruments")
    public ArrayList<Instrument> getInstruments() {
        return instruments;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getInstrumentInfo")
    public ArrayList<InstrumentInfo> getInstrumentInfo(@QueryParam("instrument") String instrument) {
        ArrayList<InstrumentInfo> instrumentInfoArray = new ArrayList<InstrumentInfo>();
        for (BandUser currentUser : bandUsers) {
            if ((currentUser.equipJson.instrument != null)  && currentUser.equipJson.instrument.equals(instrument) )
            instrumentInfoArray.add(new InstrumentInfo(currentUser.username, currentUser.equipJson));
            else if (instrument == null) {
                instrumentInfoArray.add(new InstrumentInfo(currentUser.username, currentUser.equipJson)); 
            }
        }
        return instrumentInfoArray;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getMouthpieceInfo")
    public String[] getMouthpieceInfo(@QueryParam("instrument") String instrument) {
        switch (instrument) {
            case "Clarinet": return clarinetMouthpieces; 
            case "Saxophone" : return saxophoneMouthpieces;
            default : return new String[0];
        }    
        
    }

}
