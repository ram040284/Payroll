package com.payroll.employee.attendance.business;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ListIterator;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.payroll.employee.attendance.dao.EmployeeAttendanceDAO;
import com.payroll.employee.attendance.dataobjects.EmployeeAttendance;

@Service
public class EmployeeAttendanceService {
	
	private static final DateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	
//	@Autowired
//	private EmployeeAttendanceDAO employeeAttendaceDAO;
	
	public List<EmployeeAttendance> getEmployeeAttendance() {
		return new EmployeeAttendanceDAO().getEmployeeAttendance();
	}
	
	public String addEmployeeAttendance(MultipartFile multipartFile) {
		
		String result = null;
		File file = null;
		try {
			file = convertMultiPartToFile(multipartFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Reader reader = null;
		try {
			reader = new FileReader(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		CSVParser parser = new CSVParserBuilder()
			    .withSeparator(',')
			    .withIgnoreQuotations(true)
			    .build();
		
		CSVReader csvReader = new CSVReaderBuilder(reader)
			    .withSkipLines(1)
			    .withCSVParser(parser)
			    .build();
		
		List<EmployeeAttendance> employeeAttendanceList = new ArrayList<EmployeeAttendance>();
		
		try {
			List<String[]> csvRows = csvReader.readAll();
			
			ListIterator<String[]> eachRowFromCSVRows = csvRows.listIterator();
			
			while (eachRowFromCSVRows.hasNext()) {
				String[] rd = eachRowFromCSVRows.next();
				Calendar calendar = Calendar.getInstance();
				if (rd.length != 1) {
					employeeAttendanceList
//					.add(new EmployeeAttendance(Integer.parseInt(rd[0]), rd[1], rd[2], rd[3].replace("=\"\"", ""), rd[4], rd[5], rd[6], rd[7], rd[8].charAt(0), new Timestamp(System.currentTimeMillis())));
					.add(new EmployeeAttendance(rd[1], rd[2], rd[3].replace("=\"\"", ""), rd[4], rd[5], rd[6], rd[7], rd[8].charAt(0), null, SIMPLE_DATE_FORMAT.format(calendar.getTime()), null));
				}
			}
			
			result = new EmployeeAttendanceDAO().addEmployeeAttendance(employeeAttendanceList);
			
		} catch (IOException e) {
			result = "failure";
			e.printStackTrace();
			
		}
		
		return result;
		
	}
	
	private File convertMultiPartToFile(MultipartFile file) throws IOException {
		File convFile = new File(file.getOriginalFilename());
		FileOutputStream fos = new FileOutputStream(convFile);
		fos.write(file.getBytes());
		fos.close();
		return convFile;
	}

	public List<EmployeeAttendance> getAttendance() {
		return new EmployeeAttendanceDAO().getAttendance();
	}

	public String updateEmployeeAttendance(int[] attendanceToProcess, String absenceReason) {
		return new EmployeeAttendanceDAO().updateEmployeeAttendance(attendanceToProcess, absenceReason);
		
	}

}
