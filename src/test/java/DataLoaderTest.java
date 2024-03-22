import org.example.Aws_Verify;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

public class DataLoaderTest {
    @TempDir
    Path tempDir;
    private Path testFilePath;

    @BeforeEach
    void setUp() throws IOException {
        testFilePath = tempDir.resolve("test.json");
    }

    @Test
    void testVerifyWithEmptyStatements() throws IOException,NullPointerException {
        String testJson = """
                {
                   "PolicyName": "root",
                  "PolicyDocument": {
                    "Version": "2012-10-17",
                    "Statement": []
                  }
                }""";
        Files.writeString(testFilePath, testJson);

        Aws_Verify policy = Aws_Verify.readAws_Verify(testFilePath.toString());
        boolean[] result = policy.verify();
        assertTrue(result.length == 0);
    }

    @Test
    void testVerifyWithNonMatchingResource() throws IOException,NullPointerException {
        String testJson = """
                {
                  "PolicyName": "root",
                  "PolicyDocument": {
                    "Version": "2012-10-17",
                    "Statement": [
                      {
                        "Sid": "IamListAccess",
                        "Effect": "Allow",
                        "Action": [
                          "iam:ListRoles",
                          "iam:ListUsers"
                        ],
                        "Resource": "some/resource"
                      }
                    ]
                  }
                }""";
        Files.writeString(testFilePath, testJson);

        Aws_Verify policy = Aws_Verify.readAws_Verify(testFilePath.toString());
        boolean[] result = policy.verify();
        assertTrue(result.length == 1 && result[0]);
    }

    @Test
    void testVerifyWithMatchingResource() throws IOException,NullPointerException {
        String testJson = """
                {
                  "PolicyName": "root",
                  "PolicyDocument": {
                    "Version": "2012-10-17",
                    "Statement": [
                      {
                        "Sid": "IamListAccess",
                        "Effect": "Allow",
                        "Action": [
                          "iam:ListRoles",
                          "iam:ListUsers"
                        ],
                        "Resource": "*"
                      }
                    ]
                  }
                }""";
        Files.writeString(testFilePath, testJson);

        Aws_Verify policy = Aws_Verify.readAws_Verify(testFilePath.toString());
        boolean[] result = policy.verify();
        assertFalse(result.length == 1 && result[0]);
    }

    @Test
    void testVerifyWithMultipleStatements()throws IOException,NullPointerException{
        String testJson = """
                {
                                  "PolicyName": "root",
                                  "PolicyDocument": {
                                    "Version": "2012-10-17",
                                    "Statement": [
                                      {
                                        "Sid": "IamListAccess",
                                        "Effect": "Allow",
                                        "Action": [
                                          "iam:ListRoles",
                                          "iam:ListUsers"
                                        ],
                                        "Resource": "*"
                                      },
                                      {
                                        "Sid": "S3FullAccess",
                                        "Effect": "Allow",
                                        "Action": "s3:*",
                                        "Resource": "arn:aws:s3:::example_bucket/*"
                                      }
                                    ]
                                  }
                }""";
        Files.writeString(testFilePath, testJson);
        Aws_Verify policy = Aws_Verify.readAws_Verify(testFilePath.toString());
        boolean[] result = policy.verify();
        assertTrue(result.length == 2 && !result[0] && result[1]);

    }
}
