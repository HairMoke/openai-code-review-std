curl -X POST \
        -H "Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiIsInNpZ25fdHlwZSI6IlNJR04ifQ.eyJhcGlfa2V5IjoiMTYwZWZlZGNlZjcxNDhhMWIwYzY1OTVkZTNkZDczNWYiLCJleHAiOjE3Mzk3NjI4MzkyODIsInRpbWVzdGFtcCI6MTczOTc2MTAzOTI5MX0.ImLWBNgc_OSSUTJ8KKJztFVekAwmsg7UD51CtYX3y64" \
        -H "Content-Type: application/json" \
        -H "User-Agent: Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)" \
        -d '{
          "model":"glm-4",
          "stream": "true",
          "messages": [
              {
                  "role": "user",
                  "content": "1+1"
              }
          ]
        }' \
  https://open.bigmodel.cn/api/paas/v4/chat/completions
