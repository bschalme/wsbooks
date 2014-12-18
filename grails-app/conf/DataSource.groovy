dataSource {
    pooled = true
    driverClassName = "org.h2.Driver"
    username = "sa"
    password = ""
}
hibernate {
    cache.use_second_level_cache = true
    cache.use_query_cache = false
    cache.region.factory_class = 'org.hibernate.cache.ehcache.EhCacheRegionFactory'
}
// environment specific settings
environments {
    development {
        dataSource {
            dbCreate = "update" // one of 'create', 'create-drop', 'update', 'validate', ''
            // url = "jdbc:h2:mem:devDb;MVCC=TRUE;LOCK_TIMEOUT=10000"
			dialect = org.hibernate.dialect.MySQL5InnoDBDialect
			driverClassName = 'com.mysql.jdbc.Driver'
            url = "jdbc:mysql://localhost/"
			username = 'wsbooks'
			password = 'wsbooks'
			properties {
				defaultCatalog="wsbooks"
			}
        }
        dataSource_opensync {
            dbCreate = "create-drop" // one of 'create', 'create-drop', 'update', 'validate', ''
            url = "jdbc:h2:mem:devOpensyncDb;MVCC=TRUE;LOCK_TIMEOUT=10000"
        }
    }
    test {
        dataSource {
            dbCreate = "update"
            url = "jdbc:h2:mem:testDb;MVCC=TRUE;LOCK_TIMEOUT=10000"
        }
        dataSource_opensync {
            dbCreate = "validate"
			dialect = org.hibernate.dialect.MySQLInnoDBDialect
			driverClassName = 'com.mysql.jdbc.Driver'
            url = "jdbc:mysql://anakin/"
			username = 'test-brian'
			password = 'test-brian'
			properties {
				defaultCatalog="brianopensync"
			}
        }
    }
    production {
        dataSource {
            dbCreate = "update"
			dialect = org.hibernate.dialect.MySQLInnoDBDialect
			driverClassName = 'com.mysql.jdbc.Driver'
            url = ""
			username = ""
			password = ""
            pooled = true
            properties {
			   defaultCatalog="wsbooks"
               maxActive = -1
               minEvictableIdleTimeMillis=1800000
               timeBetweenEvictionRunsMillis=1800000
               numTestsPerEvictionRun=3
               testOnBorrow=true
               testWhileIdle=true
               testOnReturn=true
               validationQuery="SELECT 1"
            }
        }
        dataSource_opensync {
            dbCreate = "validate"
			dialect = org.hibernate.dialect.MySQLInnoDBDialect
			driverClassName = 'com.mysql.jdbc.Driver'
            url = ""
			username = ""
			password = ""
            pooled = true
            properties {
			   defaultCatalog="qbairspeed"
               maxActive = -1
               minEvictableIdleTimeMillis=1800000
               timeBetweenEvictionRunsMillis=1800000
               numTestsPerEvictionRun=3
               testOnBorrow=true
               testWhileIdle=true
               testOnReturn=true
               validationQuery="SELECT 1"
            }
        }
    }
}
