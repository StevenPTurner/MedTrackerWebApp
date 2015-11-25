    package uk.ac.dundee.computing.aec.MedTracker.lib;

import java.util.ArrayList;
import java.util.List;

import com.datastax.driver.core.*;

public final class Keyspaces {

    public Keyspaces() {

    }

    public static void SetUpKeySpaces(Cluster c) {
        try {
            //Sets up the keyspace 'instagrim_swturner' 
            String createkeyspace = "create keyspace if not exists MedTracker  WITH replication = {'class':'SimpleStrategy', 'replication_factor':1}";
            //modified and extended table for user details date is handled in model with ajva
            String CreateUserProfile = "CREATE TABLE if not exists MedTracker.userprofiles (\n"
                    + "      login text PRIMARY KEY,\n"
                    + "      password text,\n"
                    + "      first_name text,\n"
                    + "      last_name text,\n"
                    + "      email text,\n"
                    + "      joinDate text\n"
                    + "  );";
            //created table to store user comments for profiles
            String CreateMedicine = "CREATE TABLE if not exists MedTracker.Medicine (\n"
                    + "      id UUID,\n"
                    + "      login text,\n"
                    + "      medicine_name text,\n"
                    + "      instructions text,\n"
                    + "      dose int,\n"
                    + "      time_between int,\n"
                    + "      doses_left int,\n"
                    + "      last_taken timestamp,\n"
                    + "      PRIMARY KEY (id, login, medicine_name)"
                    + "  );";
            Session session = c.connect();
            try {
                PreparedStatement statement = session
                        .prepare(createkeyspace);
                BoundStatement boundStatement = new BoundStatement(
                        statement);
                ResultSet rs = session
                        .execute(boundStatement);
                System.out.println("created MedTracker ");
            } catch (Exception et) {
                System.out.println("Can't create MedTracker " + et);
            }

            System.out.println("" + CreateUserProfile);
            try {
                SimpleStatement cqlQuery = new SimpleStatement(CreateUserProfile);
                session.execute(cqlQuery);
            } catch (Exception et) {
                System.out.println("Can't create user Profile " + et);
            }
            System.out.println("" + CreateMedicine);
            try {
                SimpleStatement cqlQuery = new SimpleStatement(CreateMedicine);
                session.execute(cqlQuery);
            } catch (Exception et) {
                System.out.println("Can't create Medicine " + et);
            }
            session.close();

        } catch (Exception et) {
            System.out.println("Other keyspace or coulm definition error" + et);
        }

    }
}
