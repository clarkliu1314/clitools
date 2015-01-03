package com.sangupta.clitools.file;

import io.airlift.command.Arguments;
import io.airlift.command.Command;
import io.airlift.command.HelpOption;
import io.airlift.command.SingleCommand;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import javax.inject.Inject;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

import com.sangupta.clitools.core.AbstractMultiFileTool;

@Command(name = "rtrim", description = "Remove whitespaces from end of each line of the file")
public class RightTrim extends AbstractMultiFileTool {
	
	@Inject
	private HelpOption helpOption;
	
	@Arguments(description = "File(s)/file pattern(s) to work upon")
	private List<String> arguments;
	
	private final AtomicLong bytesSaved = new AtomicLong();
	
	public static void main(String[] args) {
		RightTrim trim = SingleCommand.singleCommand(RightTrim.class).parse(args);
		
		if(trim.helpOption.showHelpIfRequested()) {
			return;
		}
		
		trim.execute(trim.arguments.toArray(com.sangupta.jerry.util.StringUtils.EMPTY_STRING_LIST));
	}
	
	@Override
	protected boolean processFile(File file) throws IOException {
		// read entire file in memory
		List<String> contents = FileUtils.readLines(file);
		final long initial = file.length();
		
		// now for each string - trim from end
		for(int index = 0; index < contents.size(); index++) {
			String line = contents.get(index);
			line = StringUtils.stripEnd(line, null);
			contents.set(index, line);
		}
		
		// write back contents of file
		FileUtils.writeLines(file, contents);
		long current = file.length();
		
		this.bytesSaved.addAndGet(initial - current);
		System.out.println("File " + file.getAbsoluteFile().getAbsolutePath() + " right-trimmed and saved " + (initial - current) + " bytes.");
		return true;
	}
	
	@Override
	protected void postProcess() {
		System.out.println("\nTotal savings: " + this.bytesSaved.get() + " bytes!");
	}

}