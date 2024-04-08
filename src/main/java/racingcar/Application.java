package racingcar;

import camp.nextstep.edu.missionutils.Console;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

class Car {
    private String name;
    private int position;

    public Car(String name) {
        this.name = name;
        this.position = 0;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public void move() {
        Random rand = new Random();
        int moveValue = rand.nextInt(10); // 0부터 9까지의 난수 생성
        if (moveValue >= 4) {
            position++;
            System.out.println(name + " 전진! 현재 위치: " + position);
        } else {
            System.out.println(name + " 멈춤! 현재 위치: " + position);
        }
    }
}

public class Application {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("이동할 횟수를 입력하세요: ");
        int moveCount = scanner.nextInt();
        scanner.nextLine(); // 버퍼 비우기

        if (moveCount <= 0) {
            throw new IllegalArgumentException("이동 횟수는 1 이상이어야 합니다.");
        }

        System.out.print("참가할 자동차 이름을 쉼표(,)로 구분하여 입력하세요 (최대 5자 이내): ");
        String input = scanner.nextLine();
        String[] carNames = input.split(",");
        List<Car> cars = new ArrayList<>();

        for (String name : carNames) {
            if (name.length() > 5) {
                throw new IllegalArgumentException("자동차 이름은 5자 이하이어야 합니다.");
            }
            cars.add(new Car(name));
        }

        System.out.println("\n자동차 경주를 시작합니다!");

        for (int i = 0; i < moveCount; i++) {
            System.out.println("\n" + (i + 1) + "회째 이동 결과");
            for (Car car : cars) {
                car.move();
            }
        }

        int maxPosition = 0;
        for (Car car : cars) {
            maxPosition = Math.max(maxPosition, car.getPosition());
        }

        System.out.print("\n우승자는 ");
        boolean firstWinner = true;
        for (Car car : cars) {
            if (car.getPosition() == maxPosition) {
                if (!firstWinner) {
                    System.out.print(", ");
                }
                System.out.print(car.getName());
                firstWinner = false;
            }
        }
        System.out.println(" 입니다!");
    }
}
