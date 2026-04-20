docker compose up -d

#await 15s
echo "Waiting for LocalStack to start..."
sleep 15
echo "LocalStack started"

# Create Table
aws --endpoint="http://localhost:4566" dynamodb create-table \
  --region "sa-east-1" \
  --table-name "tb_users" \
  --attribute-definitions \
    "AttributeName=user_id,AttributeType=S" \
    "AttributeName=email,AttributeType=S" \
  --key-schema \
    "AttributeName=user_id,KeyType=HASH" \
  --global-secondary-indexes \
    '[
      {
        "IndexName": "email-index",
        "KeySchema": [
          {"AttributeName": "email", "KeyType": "HASH"}
        ],
        "Projection": {
          "ProjectionType": "KEYS_ONLY"
        },
        "ProvisionedThroughput": {
          "ReadCapacityUnits": 5,
          "WriteCapacityUnits": 5
        }
      }
    ]' \
  --provisioned-throughput \
      "ReadCapacityUnits=5,WriteCapacityUnits=5" \
  --query "TableDescription.TableName" \
  --output text