max=${MAX_ATTEMPTS:-3}
i=0

while [ "$i" -lt "$max" ]; do
  if redis-cli quit; then
    echo "Server ready..."
    if [ "$(redis-cli get seeded)" != "true" ]; then
      echo "Seeding data..."
      redis-cli --pipe < /tmp/seed.txt
      echo "Data seeded..."
    fi
    rm -f /tmp/seed.txt
    echo "Seed file removed..."
    break
  else
    echo "Server not ready. Wait then retry $((max - 1 - i)) more time(s)..."
    sleep 3
  fi
  i=$((i + 1))
done
