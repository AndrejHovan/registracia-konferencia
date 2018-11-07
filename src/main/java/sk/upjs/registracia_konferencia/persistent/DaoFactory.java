package sk.upjs.registracia_konferencia.persistent;

import org.springframework.jdbc.core.JdbcTemplate;

import com.mysql.cj.jdbc.MysqlDataSource;

public enum DaoFactory {
	INSTANCE;
	
	private JdbcTemplate jdbcTemplate;
	private ParticipantDao participantDao;
	private WorkshopDao workshopDao;
	
	public ParticipantDao getParticipantDao() {
		if(participantDao == null)
			participantDao = new MemoryParticipantDao();
		return participantDao;
	}
	
	public WorkshopDao getWorkshopDao() {
		if(workshopDao == null) {
			workshopDao = new MysqlWorkshopDao(getJdbcTemplate());
		}
		return workshopDao;
	}
	
	private JdbcTemplate getJdbcTemplate() {
		if(jdbcTemplate == null) {
			MysqlDataSource dataSource = new MysqlDataSource();
			dataSource.setUser("registracia_konferencia");
			dataSource.setPassword("paz1c");
			//dataSource.setDatabaseName("registracia_konferencia");
			dataSource.setUrl("jdbc:mysql://localhost/registracia_konferencia?serverTimezone=Europe/Bratislava");
			jdbcTemplate = new JdbcTemplate(dataSource);
		}
		return jdbcTemplate;
	}
}
