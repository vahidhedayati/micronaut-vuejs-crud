package services;

import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.boot.spi.MetadataImplementor;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.hbm2ddl.Target;
import org.hibernate.tool.schema.TargetType;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.jdbc.support.MetaDataAccessException;

import java.io.File;
import java.util.EnumSet;

//Test class to behave like schema-export under grails
//https://burtbeckwith.com/blog/?m=200803
//https://www.programcreek.com/java-api-examples/?api=org.hibernate.tool.hbm2ddl.SchemaExport

public class DbExtractService {
    public  void extraDb() {
        String file = "tmp.sql";
        new File(file).delete();

        MetadataSources metadata = new MetadataSources(new StandardServiceRegistryBuilder()
                .applySetting("hibernate.dialect", "org.hibernate.dialect.H2Dialect").build());
        //metadata.addAnnotatedClass(User.class);
        SchemaExport export = new SchemaExport();
        export.setOutputFile(file);
        export.setDelimiter(";");
        export.setFormat(true);
        export.execute(EnumSet.of(TargetType.SCRIPT), SchemaExport.Action.CREATE, metadata.buildMetadata());
    }

   public  void extraDb1() {

        /*
       DriverManagerDataSource ds = new DriverManagerDataSource();
       ds.setDriverClassName("org.h2.Driver");
       ds.setUrl("jdbc:h2:mem:default;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE");
       ds.setUsername("sa");
       ds.setPassword("");
       try {
           String dbName = JdbcUtils.extractDatabaseMetaData(ds, "getDatabaseProductName");
           int majorVersion = JdbcUtils.extractDatabaseMetaData(ds, "getDatabaseMajorVersion");
           //DialectFactory.determineDialect(dbName, majorVersion).class.name

       } catch (MetaDataAccessException e) {

       }
       */

       /*MetadataSources metadata = new MetadataSources(
               new StandardServiceRegistryBuilder().configure().build());

        */

       /*
       MetadataSources metadata = new MetadataSources(new StandardServiceRegistryBuilder()
               .applySetting("hibernate.dialect", "org.hibernate.dialect.H2Dialect").build());

       SchemaExport export = new SchemaExport((MetadataImplementor) metadata.buildMetadata());
       export.create(Target.SCRIPT);
       Configuration cfg = new Configuration();
*/
/*
       SchemaExport se = new SchemaExport(cfg);
       se.setOutputFile("/tmp/export.sql");
       se.setDelimiter(";");
       se.setFormat(false);

 */
       //se.create(TargetType.DATABASE, Metadata);

       /*
           SchemaExport schemaExport = new SchemaExport(cfg)
                   .setHaltOnError(true)
                   .setOutputFile(file.path)
                   .setDelimiter(';');
*/


       //  cfg.setProperty("hibernate.dialect", Config.HIBERNATE_DIALECT);
       //   cfg.setProperty("hibernate.connection.datasource", Config.HIBERNATE_DATASOURCE);
      /* cfg.setProperty("hibernate.hbm2ddl.auto", hbm2ddl);
       cfg.setProperty("hibernate.show_sql", Config.HIBERNATE_SHOW_SQL);
       cfg.setProperty("hibernate.generate_statistics", Config.HIBERNATE_STATISTICS);
       cfg.setProperty("hibernate.search.analyzer", Config.HIBERNATE_SEARCH_ANALYZER);
       cfg.setProperty("hibernate.search.default.directory_provider", "org.hibernate.search.store.FSDirectoryProvider");
       cfg.setProperty("hibernate.search.default.indexBase", Config.HIBERNATE_SEARCH_INDEX_HOME);
       cfg.setProperty("hibernate.search.default.exclusive_index_use", Config.HIBERNATE_SEARCH_INDEX_EXCLUSIVE);
       cfg.setProperty("hibernate.search.default.optimizer.operation_limit.max", "500");
       cfg.setProperty("hibernate.search.default.optimizer.transaction_limit.max", "75");
       cfg.setProperty("hibernate.worker.execution", "async");
       */
   }

}

