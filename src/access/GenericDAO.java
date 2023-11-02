/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package access;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import services.Facade;

/**
 *
 * @author sofia
 */
public class GenericDAO<T> {

    private String key = null;
    private T result = null;
    private List<T> list;
    private final Class<T> tipoGenerico;

    public GenericDAO(Class<T> tipo) {
        this.tipoGenerico = tipo;
    }

    public Boolean save(String key, Map<String, Object> object, String pathChild) {
        FirebaseDatabase connexion = null;
        try {
            connexion = Facade.getConnection();
            DatabaseReference ref = connexion.getReference("/");
            DatabaseReference childReference = ref.child(pathChild + key);
            childReference.setValue(object);
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(GenericDAO.class.getName()).log(Level.SEVERE, null,
                    ex);
            return false;
        }
        return true;
    }

    public Boolean update(String key, Map<String, Object> object, String pathChild) {
        FirebaseDatabase connexion = null;
        try {
            connexion = Facade.getConnection();
            final DatabaseReference dataBase = connexion.getReference("/").child(pathChild);

            dataBase.updateChildren(object);
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            //Logger.getLogger(ItemDAO.class.getName()).log(Level.SEVERE, null,         ex);
        }
        return null;
    }

    public String key(String atributo, String valorAtributo, String pathChild) {
        FirebaseDatabase connexion = null;
        try {
            connexion = Facade.getConnection();
            DatabaseReference ref = connexion.getReference("/").child(pathChild);
            Query query = ref.orderByChild(atributo).equalTo(valorAtributo);
            query.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                            key = userSnapshot.getKey();
                        }
                    } else {
                        System.out.println("No se encontraron usuarios con ese documento de usuario.");
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    System.out.println("La lectura fallo  " + databaseError.getCode());
                }
            });
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(GenericDAO.class.getName()).log(Level.SEVERE, null,
                    ex);
        }
        return key;
    }

    public T recover(String atributo, String valorAtributo, String pathChild) {
        FirebaseDatabase connexion = null;
        try {
            connexion = Facade.getConnection();
            DatabaseReference ref = connexion.getReference("/").child(pathChild);
            Query query = ref.orderByChild(atributo).equalTo(valorAtributo);
            query.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                            Map<String, Object> object = (Map<String, Object>) userSnapshot.getValue();
                            ObjectMapper objectMapper = new ObjectMapper();
                            System.out.println("object " + object);
                            result = (T) objectMapper.convertValue(object, getTipoGenerico());
                        }
                    } else {
                        System.out.println("No se encontro");
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    System.out.println("La lectura fallo  " + databaseError.getCode());
                }
            });
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(GenericDAO.class.getName()).log(Level.SEVERE, null,
                    ex);
        }
        return result;
    }


    public List<T> list(String pathChild) {
        FirebaseDatabase connexion = null;
        this.list = new ArrayList<>();
        //try {
        connexion = Facade.getConnection();
        DatabaseReference ref = connexion.getReference("/").child("usuarios");
        ref.addValueEventListener(new ValueEventListener() {
            
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            GenericTypeIndicator<Map<String, Object>> typeIndicator = new GenericTypeIndicator<Map<String, Object>>() {};
            Map<String, Object> objects = dataSnapshot.getValue(typeIndicator);
            //System.out.println("list usuarios: " + objects);            
            for (Map.Entry<String, Object> entry : objects.entrySet()) {
                Object value = entry.getValue();
                ObjectMapper objectMapper = new ObjectMapper();
                T res = (T) objectMapper.convertValue(value, getTipoGenerico());
                list.add(res);
            }
        }
        @Override
        public void onCancelled(DatabaseError databaseError) {
            System.out.println("The read failed: " + databaseError.getCode());
        }
        });
        return list;
    }

    

    public Class<T> getTipoGenerico() {
        return tipoGenerico;
    }

}
