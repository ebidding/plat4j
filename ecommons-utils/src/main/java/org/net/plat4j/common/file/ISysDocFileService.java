package org.net.plat4j.common.file;

import org.net.plat4j.web.model.SysDocFile;

public interface ISysDocFileService {
	SysDocFile getSysDocFileById(String fileId);
	Object save(SysDocFile sysDocFile);
	Object update(Long fileId, SysDocFile sysDocFile);
	Object update(SysDocFile sysDocFile);
}
