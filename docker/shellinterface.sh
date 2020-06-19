
cp $1 ./input/input.csv
sudo docker-compose up --build -d

while true; do
    echo "Please enter the route (XXX-XXX):"
    read route

    curl http://localhost:8080/query/route?route=$route
done