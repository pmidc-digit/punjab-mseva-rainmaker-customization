package org.egov.migrationkit.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import io.swagger.client.model.LocalDocument;

@Component
public class SWDocumentRowmapper implements ResultSetExtractor<List<LocalDocument>> {

	@Override
	public List<LocalDocument> extractData(ResultSet rs) throws SQLException, DataAccessException {
		List<LocalDocument> documentList = new ArrayList<>();
		while (rs.next()) {
			LocalDocument document = new LocalDocument();
			document.setConnectiondetailsid(rs.getString("applicationdetails"));
			document.setContenttype(rs.getString("contenttype"));
			document.setDocumentname(rs.getString("description"));
			document.setFilename(rs.getString("filename"));
			document.setFilestoreid(rs.getString("filestoreid"));
			document.setConnectionNo(rs.getString("shsc_number"));
			documentList.add(document);
		}

		return documentList;
	}
}