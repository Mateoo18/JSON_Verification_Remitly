import org.example.Aws_Verify;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
public class DataWriterTest {
    @TempDir
    Path tempDir;
    private Path testFilePath,testFilePath2;

    @BeforeEach
    void setUp() throws IOException {
        testFilePath = tempDir.resolve("test.json");
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

        testFilePath2 = tempDir.resolve("test2.json");
        String testJson2 = """
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
        Files.writeString(testFilePath2, testJson2);
    }

    @Test
    void testLoadIAMPolicy1() {
        String path = testFilePath.toString();
        Aws_Verify policy = Aws_Verify.readAws_Verify(path);

        assertEquals("root", policy.getName());
        JSONObject document = policy.getDocument();
        assertEquals("2012-10-17", document.getString("Version"));
        JSONArray statements = policy.getStatements();
        assertEquals(1, statements.length());
        JSONObject statement = statements.getJSONObject(0);
        assertTrue(statement.getJSONArray("Action").toList().contains("iam:ListRoles"));
        assertTrue(statement.getJSONArray("Action").toList().contains("iam:ListUsers"));
        assertEquals("*", statement.getString("Resource"));
    }
    @Test
    void testLoadIAMPolicy2() {
        String path = testFilePath2.toString();
        Aws_Verify policy = Aws_Verify.readAws_Verify(path);

        assertEquals("root", policy.getName());
        JSONObject document = policy.getDocument();
        assertEquals("2012-10-17", document.getString("Version"));

        JSONArray statements = policy.getStatements();
        assertEquals(2, statements.length());

        JSONObject statement1 = statements.getJSONObject(0);
        assertTrue(statement1.getJSONArray("Action").toList().contains("iam:ListRoles"));
        assertTrue(statement1.getJSONArray("Action").toList().contains("iam:ListUsers"));
        assertEquals("*", statement1.getString("Resource"));

        JSONObject statement2 = statements.getJSONObject(1);
        assertEquals("S3FullAccess", statement2.getString("Sid"));
        assertEquals("Allow", statement2.getString("Effect"));
        assertEquals("s3:*", statement2.getString("Action"));
        assertEquals("arn:aws:s3:::example_bucket/*", statement2.getString("Resource"));
    }

}
