package core.workers.sshFileTransfer;

import net.schmizz.sshj.SSHClient;
import net.schmizz.sshj.sftp.SFTPClient;
import net.schmizz.sshj.transport.verification.PromiscuousVerifier;

import java.io.IOException;

public class SSHFileTransfer {

    private final String serverName= "192.168.102.162";
    private final int port = 22;
    private final String userName = "watney";
    private final String password = "123456";

    private SSHClient setupSSH() throws IOException {
        SSHClient client = new SSHClient();
        client.addHostKeyVerifier(new PromiscuousVerifier());
        client.connect(serverName,port);
        client.authPassword(userName,password);
        return client;
    }

    public void uploadFile(String sourceFilePath, String destinationPath){
        SSHClient sshClient = null;
        try {
            sshClient = setupSSH();
            SFTPClient sftpClient = sshClient.newSFTPClient();
            sftpClient.put(sourceFilePath,destinationPath);

            sftpClient.close();
            sshClient.disconnect();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
