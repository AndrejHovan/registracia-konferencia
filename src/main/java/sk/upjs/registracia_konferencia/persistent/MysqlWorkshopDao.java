package sk.upjs.registracia_konferencia.persistent;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import sk.upjs.registracia_konferencia.entities.Workshop;

public class MysqlWorkshopDao implements WorkshopDao {

	private JdbcTemplate jdbcTemplate;

	public MysqlWorkshopDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<Workshop> getAll() {
		String sql = "SELECT id, name, start, end, price_full, price_student, price_full_late, price_student_late FROM workshop";
		// return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Workshop.class));
		return jdbcTemplate.query(sql, new RowMapper<Workshop>() {

			@Override
			public Workshop mapRow(ResultSet rs, int rowNum) throws SQLException {
				Workshop workshop = new Workshop();
				workshop.setId(rs.getLong("id"));
				workshop.setName(rs.getString("name"));
				Timestamp timestamp = rs.getTimestamp("start");
				if (timestamp != null) {
					workshop.setStart(timestamp.toLocalDateTime().toLocalDate());
				}
				Timestamp timestamp2 = rs.getTimestamp("end");
				if (timestamp2 != null) {
					workshop.setStart(timestamp2.toLocalDateTime().toLocalDate());
				}
				workshop.setPriceFull(rs.getDouble("price_full"));
				workshop.setPriceStudent(rs.getDouble("price_student"));
				workshop.setPriceFullLate(rs.getDouble("price_full_late"));
				workshop.setPriceStudentLate(rs.getDouble("price_student_late"));
				return workshop;
			}

		});
	}

	@Override
	public Workshop save(Workshop workshop) {
		if (workshop == null)
			return null;
		if (workshop.getId() == null) { // CREATE
			SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
			simpleJdbcInsert.withTableName("workshop");
			simpleJdbcInsert.usingGeneratedKeyColumns("id");
			simpleJdbcInsert.usingColumns("name", "start", "end", "price_full", "price_student", "price_full_late",
					"price_student_late");
			Map<String, Object> values = new HashMap<>();
			values.put("name", workshop.getName());
			values.put("start", workshop.getStart());
			values.put("end", workshop.getEnd());
			values.put("price_full", workshop.getPriceFull());
			values.put("price_student", workshop.getPriceStudent());
			values.put("price_full_late", workshop.getPriceFullLate());
			values.put("price_student_late", workshop.getPriceStudentLate());
			Long id = simpleJdbcInsert.executeAndReturnKey(values).longValue();
			workshop.setId(id);
		} else { // UPDATE
			String sql = "UPDATE workshop SET"
					+ " name = ?, start = ?, end = ?, price_full = ?, price_student = ?, "
					+ "price_full_late = ?, price_student_late = ?" + " WHERE id = ?";
			jdbcTemplate.update(sql, workshop.getName(), workshop.getStart(),
					workshop.getEnd(), workshop.getPriceFull(),
					workshop.getPriceStudent(), workshop.getPriceFullLate(),
					workshop.getPriceStudentLate(), workshop.getId());
		}
		return workshop;
	}

	@Override
	public void delete(long id) {
		String sql = "DELETE FROM workshop WHERE id = " + id; 
		jdbcTemplate.update(sql);
	}

}
