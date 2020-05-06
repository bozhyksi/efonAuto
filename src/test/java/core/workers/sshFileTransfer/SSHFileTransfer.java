package core.workers.sshFileTransfer;

import net.schmizz.sshj.SSHClient;
import net.schmizz.sshj.sftp.SFTPClient;
import net.schmizz.sshj.transport.verification.PromiscuousVerifier;

import java.io.IOException;

public class SSHFileTransfer {

    private static final String serverName= "192.168.102.162";
    private static final int port = 22;
    private static final String userName = "watney";
    private static final String password = "123456";

    private static SSHClient setupSSH() throws IOException {
        SSHClient client = new SSHClient();
        client.addHostKeyVerifier(new PromiscuousVerifier());
        client.connect(serverName,port);
        client.authPassword(userName,password);
        return client;
    }

    public static void uploadFile(String sourceFilePath, String destinationPath){
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
