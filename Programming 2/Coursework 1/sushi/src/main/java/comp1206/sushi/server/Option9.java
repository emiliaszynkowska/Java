package comp1206.sushi.server;
import com.teamdev.jxmaps.Map;
import com.teamdev.jxmaps.swing.MapView;
import com.teamdev.jxmaps.*;
import comp1206.sushi.common.*;
import java.util.*;
import javax.swing.*;

public class Option9 extends Option {

    HashMap<String, LatLng> points;
    
    public Option9(ServerInterface server) {
        this.server = server;
        create();
    }

    @Override
    public void create() {

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        addTitle(sushiPurple, "Map", "map.png");
        changeFont(this, sushiFont);

        MapView mapview = new MapView();
        add(mapview);

        mapview.setOnMapReadyHandler(new MapReadyHandler() {
            @Override
            public void onMapReady(MapStatus status) {
                if (status == MapStatus.MAP_STATUS_OK) {

                    getData();

                    Map map = mapview.getMap();
                    map.setCenter(new LatLng(50.922706, -1.396458));
                    map.setZoom(12.0);

                    MapOptions options = new MapOptions();
                    MapTypeControlOptions controlOptions = new MapTypeControlOptions();
                    controlOptions.setPosition(ControlPosition.TOP_RIGHT);
                    options.setMapTypeControlOptions(controlOptions);
                    map.setOptions(options);

                    for(java.util.Map.Entry<String, LatLng> entry : points.entrySet()) {
                        String name = entry.getKey();
                        LatLng latlng = entry.getValue();

                        Marker marker = new Marker(map);
                        marker.setTitle(name);
                        marker.setPosition(latlng);

                        MarkerLabel label = new MarkerLabel();
                        label.setText(name);
                        label.setFontWeight("bold");
                        label.setColor("white");
                        marker.setLabel(label);
                    }
                }
            }
        });
    }

    @Override
    public void getData() {
        points = new HashMap<String, LatLng>();
        for(Supplier supplier : server.getSuppliers()) {
            LatLng location = new LatLng(supplier.getPostcode().getLatLong().get("lat"), supplier.getPostcode().getLatLong().get("lon"));
            points.put(supplier.getName(), location);
        }
        for(User user : server.getUsers()) {
            LatLng location = new LatLng(user.getPostcode().getLatLong().get("lat"), user.getPostcode().getLatLong().get("lon"));
            points.put(user.getName(), location);
        }
        LatLng location = new LatLng(server.getRestaurantPostcode().getLatLong().get("lat"), server.getRestaurantPostcode().getLatLong().get("lon"));
        points.put(server.getRestaurantName(), location);
    }
    
}
