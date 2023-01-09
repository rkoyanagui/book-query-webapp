FROM redis/redis-stack:6.2.6-v0
COPY ./seed-entrypoint.sh /tmp/seed-entrypoint.sh
COPY ./init.sh /tmp/init.sh
COPY ./seed.txt /tmp/seed.txt
ENTRYPOINT ["/tmp/seed-entrypoint.sh"]
